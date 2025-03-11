package com.rr.file_wizard.application.service;

import com.rr.file_wizard.infrastructure.exception.FileUploadException;
import com.rr.file_wizard.infrastructure.exception.FileValidationException;
import com.rr.file_wizard.domain.FileMetadata;
import com.rr.file_wizard.adapters.outbound.repository.FileMetadataRepository;
import com.rr.file_wizard.infrastructure.response.ApiResponse;
import com.rr.file_wizard.utils.ChecksumUtilImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileMetadataService {

    private final S3Service s3Service;
    private final FileMetadataRepository fileMetadataRepository;

    public FileMetadataService(S3Service s3Service, FileMetadataRepository fileMetadataRepository) {
        this.s3Service = s3Service;
        this.fileMetadataRepository = fileMetadataRepository;
    }

    public FileMetadata saveMetadata(MultipartFile file) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String formattedDate = myDateObj.format(myFormatObj);

        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFileName(file.getOriginalFilename());
        fileMetadata.setContentType(file.getContentType());

        ChecksumUtilImpl checksumUtil = new ChecksumUtilImpl();
        fileMetadata.setChecksum(checksumUtil.computeChecksum(file, 8192));
        fileMetadata.setChecksumAlgorithm(checksumUtil.getChecksumMethod());
        fileMetadata.setBucketFileName(formattedDate + "." + file.getOriginalFilename());

        fileMetadata.setFileExtension("unknown");
        int index = file.getOriginalFilename().lastIndexOf(".");
        if (index != -1 && index < file.getOriginalFilename().length() - 1) {
            fileMetadata.setFileExtension(file.getOriginalFilename().substring(index + 1));
        }

        fileMetadataRepository.save(fileMetadata);

        return fileMetadata;
    }

    @Transactional(rollbackFor = FileUploadException.class)
    public ApiResponse<String> uploadFile(MultipartFile file) throws FileUploadException {

        if (file == null || file.isEmpty()) {
            throw new FileValidationException("File must not be null or empty");
        }

        FileMetadata fileMetadata = saveMetadata(file);
        String result = s3Service.uploadFile(fileMetadata.getBucketFileName(), file, fileMetadata.getFileExtension());

        return ApiResponse.success(result);
    }

    public ApiResponse<Map<String, Object>> listFiles(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FileMetadata> bucketFiles = fileMetadataRepository.findAll(pageable);

        List<Map<String, Object>> files = new ArrayList<>();

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        for (FileMetadata bucketFile : bucketFiles) {
            Map<String, Object> info = new HashMap<>();

            String bucketFileName = bucketFile.getBucketFileName();
            if (bucketFileName != null && bucketFileName.contains(".")) {
                String unformattedDate = bucketFileName.split("\\.")[0];

                try {
                    LocalDateTime dateTime = LocalDateTime.parse(unformattedDate, inputFormatter);
                    info.put("updated_date", dateTime.format(outputFormatter));
                } catch (Exception e) {
                    info.put("updated_date", "Invalid date format"); // Fallback value
                }
            } else {
                info.put("updated_date", "Unknown");
            }

            info.put("file_name", bucketFile.getFileName());
            info.put("content_type", bucketFile.getContentType());
            info.put("file_extension", bucketFile.getFileExtension());
            info.put("file_id", bucketFile.getBucketFileName());

            files.add(info);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("files", files);
        response.put("current_page", bucketFiles.getNumber());
        response.put("total_items", bucketFiles.getTotalElements());
        response.put("total_pages", bucketFiles.getTotalPages());

        return ApiResponse.success(response);
    }
}
