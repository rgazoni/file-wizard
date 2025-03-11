package com.rr.file_wizard.utils;

import org.springframework.web.multipart.MultipartFile;

public interface ChecksumUtil {

    long computeChecksum(MultipartFile file, int bufferSize);
    String getChecksumMethod();
}
