package com.rr.file_wizard.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity(name = "filemetadata")
public class FileMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "file_name")
    private String fileName;

    @NotEmpty
    @Column(name = "content_type")
    private String contentType;

    @NotNull
    @Column(name = "checksum")
    private long checksum;

    @NotEmpty
    @Column(name = "checksum_algorithm")
    private String checksumAlgorithm;

    @NotEmpty
    @Column(name = "bucket_file_name")
    private String bucketFileName;

    @NotEmpty
    @Column(name = "file_extension")
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

    public void setId(Integer id) {
        this.id = id;
    }

}
