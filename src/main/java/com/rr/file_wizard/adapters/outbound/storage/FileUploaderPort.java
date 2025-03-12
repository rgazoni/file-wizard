package com.rr.file_wizard.adapters.outbound.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploaderPort {
    String uploadFile(String fileName, MultipartFile file, String fileExtension);
}
