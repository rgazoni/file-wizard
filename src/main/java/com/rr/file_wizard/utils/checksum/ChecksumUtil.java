package com.rr.file_wizard.utils.checksum;

import org.springframework.web.multipart.MultipartFile;

public interface ChecksumUtil {

    long computeChecksum(MultipartFile file, int bufferSize);
    String getChecksumMethod();
}
