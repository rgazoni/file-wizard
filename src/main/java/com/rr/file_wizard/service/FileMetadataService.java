package com.rr.file_wizard.service;

import com.rr.file_wizard.model.FileMetadata;
import com.rr.file_wizard.repository.FileMetadataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

@Service
public class FileMetadataService {

    private final S3Service s3Service;
    private final FileMetadataRepository fileMetadataRepository;

    public FileMetadataService(S3Service s3Service, FileMetadataRepository fileMetadataRepository) {
        this.s3Service = s3Service;
        this.fileMetadataRepository = fileMetadataRepository;
    }

    private static long getChecksum(MultipartFile file, int bufferSize) throws IOException {
        Checksum checksum = new CRC32();

        try (CheckedInputStream checkedInputStream = new CheckedInputStream(file.getInputStream(), checksum)) {
            byte[] buffer = new byte[bufferSize];
            while (checkedInputStream.read(buffer) >= 0) {}
        }

        return checksum.getValue();
    }

    public String uploadFile(MultipartFile file) throws IOException {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String formattedDate = myDateObj.format(myFormatObj);

        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFileName(file.getOriginalFilename());
        fileMetadata.setContentType(file.getContentType());

        fileMetadata.setChecksum(getChecksum(file, 8192));
        //TODO: Create a separate class
        fileMetadata.setChecksumAlgorithm("CRC32");
        //TODO: Pass this hash into a s3Service as file_Name
        fileMetadata.setBucketFileName(formattedDate + "." + file.getOriginalFilename());

        fileMetadataRepository.save(fileMetadata);

        return s3Service.uploadFile(fileMetadata.getBucketFileName(), file);
    }
}
