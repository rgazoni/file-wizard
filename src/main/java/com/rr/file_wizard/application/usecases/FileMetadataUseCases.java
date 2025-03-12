package com.rr.file_wizard.application.usecases;

import com.rr.file_wizard.domain.filemetadata.FileMetadata;
import com.rr.file_wizard.infrastructure.exception.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileMetadataUseCases {
    public FileMetadata saveMetadata(MultipartFile file);
    public String uploadFile(MultipartFile file) throws FileUploadException;
    public Map<String, Object> listFiles(int page, int size);
}
