package com.rr.file_wizard.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("FILEMETADATA")
public class FileMetadata {
    @Id
    private Integer id;

    @NotEmpty
    private String fileName;

    @NotEmpty
    private String contentType;

    @NotEmpty
    private long checksum;

    @NotEmpty
    private String checksumAlgorithm;

    @NotEmpty
    private String bucketFileName;

    @NotEmpty
    private String fileExtension;

    public FileMetadata() {}

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getBucketFileName() {
        return bucketFileName;
    }

    public void setBucketFileName(String bucketName) {
        this.bucketFileName = bucketName;
    }

    public String getChecksumAlgorithm() {
        return checksumAlgorithm;
    }

    public void setChecksumAlgorithm(String checksumAlgorithm) {
        this.checksumAlgorithm = checksumAlgorithm;
    }


    public long getChecksum() {
        return checksum;
    }

    public void setChecksum(long checksum) {
        this.checksum = checksum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
