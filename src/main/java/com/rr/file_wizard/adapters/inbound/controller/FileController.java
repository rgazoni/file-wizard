package com.rr.file_wizard.adapters.inbound.controller;
import com.rr.file_wizard.infrastructure.exception.FileValidationException;
import com.rr.file_wizard.infrastructure.response.ApiResponse;
import com.rr.file_wizard.application.service.FileMetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/v1")
public class FileController {

    private final FileMetadataService fileMetadataService;

    public FileController(FileMetadataService fileMetadataService) {
        this.fileMetadataService = fileMetadataService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileValidationException("File must not be null or empty");
        }
        return ResponseEntity.ok(fileMetadataService.uploadFile(file));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> listFiles(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(
               fileMetadataService.listFiles(page, size)
        );
    }
}
