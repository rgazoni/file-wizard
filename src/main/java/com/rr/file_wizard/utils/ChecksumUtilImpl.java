package com.rr.file_wizard.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

public class ChecksumUtilImpl implements ChecksumUtil {
    static String checksumMethod = "CRC32";

    public ChecksumUtilImpl() {}

    public long computeChecksum(MultipartFile file, int bufferSize) {
        Checksum checksum = new CRC32();

        try (CheckedInputStream checkedInputStream = new CheckedInputStream(file.getInputStream(), checksum)) {
            byte[] buffer = new byte[bufferSize];
            while (checkedInputStream.read(buffer) >= 0) {}
        } catch (IOException e) {
            throw new RuntimeException("Failed to compute checksum for file: " + file.getOriginalFilename(), e);
        }

        return checksum.getValue();
    }

    @Override
    public String getChecksumMethod() {
        return checksumMethod;
    }
}
