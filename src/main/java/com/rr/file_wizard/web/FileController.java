package com.rr.file_wizard.web;
import com.rr.file_wizard.service.FileMetadataService;
import com.rr.file_wizard.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class FileController {

    private final S3Service s3Service;
    private final FileMetadataService fileMetadataService;

    public FileController(S3Service s3Service, FileMetadataService fileMetadataService) {
        this.s3Service = s3Service;
        this.fileMetadataService = fileMetadataService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileMetadataService.uploadFile(file));
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        return ResponseEntity.ok(s3Service.listFiles());
    }
}
