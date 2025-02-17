package com.rr.file_wizard.service;
import com.rr.file_wizard.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(String fileName, MultipartFile file) {
        try {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .build(),
                    RequestBody.fromBytes(file.getBytes()));
        } catch (IOException e) {
            throw new FileUploadException("Failed to upload file. Please try again.");
        }

        return "File uploaded: " + fileName;
    }

    public byte[] downloadFile(String key) throws IOException {
        var objectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        var response = s3Client.getObject(objectRequest);
        return Files.readAllBytes(Paths.get(response.response().toString()));
    }

    public List<String> listFiles() {
        return s3Client.listObjectsV2(req -> req.bucket(bucketName))
                .contents()
                .stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }
}
