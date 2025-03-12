package com.rr.file_wizard.domain.filemetadata;

public class FileMetadata {

    private Integer id;
    private String fileName;
    private String contentType;
    private long checksum;
    private String checksumAlgorithm;
    private String bucketFileName;
    private String fileExtension;


    public FileMetadata(Integer id, String fileName, String contentType, long checksum, String checksumAlgorithm, String bucketFileName, String fileExtension) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.checksum = checksum;
        this.checksumAlgorithm = checksumAlgorithm;
        this.bucketFileName = bucketFileName;
        this.fileExtension = fileExtension;
    }

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
