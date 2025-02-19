package com.rr.file_wizard.web;
import com.rr.file_wizard.exception.FileValidationException;
import com.rr.file_wizard.service.FileMetadataService;
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
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileValidationException("File must not be null or empty");
        }
        return ResponseEntity.ok(fileMetadataService.uploadFile(file));
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> listFiles() {
        return ResponseEntity.ok(
               fileMetadataService.listFiles()
        );
    }
}
