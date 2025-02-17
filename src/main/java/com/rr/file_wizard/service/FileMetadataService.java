package com.rr.file_wizard.service;

import com.rr.file_wizard.exception.FileUploadException;
import com.rr.file_wizard.exception.FileValidationException;
import com.rr.file_wizard.model.FileMetadata;
import com.rr.file_wizard.repository.FileMetadataRepository;
import com.rr.file_wizard.util.ChecksumUtilImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        fileMetadataRepository.save(fileMetadata);

        return fileMetadata;
    }

    @Transactional(rollbackFor = FileUploadException.class)
    public String uploadFile(MultipartFile file) throws FileUploadException {

        if (file == null || file.isEmpty()) {
            throw new FileValidationException("File must not be null or empty");
        }

        FileMetadata fileMetadata = saveMetadata(file);
        return s3Service.uploadFile(fileMetadata.getBucketFileName(), file);
    }
}
