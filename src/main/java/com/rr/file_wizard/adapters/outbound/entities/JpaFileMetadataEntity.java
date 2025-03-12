package com.rr.file_wizard.adapters.outbound.entities;

import com.rr.file_wizard.domain.filemetadata.FileMetadata;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "filemetadata")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JpaFileMetadataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "checksum", nullable = false)
    private long checksum;

    @Column(name = "checksum_algorithm", nullable = false)
    private String checksumAlgorithm;

    @Column(name = "bucket_file_name", nullable = false)
    private String bucketFileName;

    @Column(name = "file_extension", nullable = false)
    private String fileExtension;

    public JpaFileMetadataEntity(FileMetadata fileMetadata) {
        this.fileName = fileMetadata.getFileName();
        this.contentType = fileMetadata.getContentType();
        this.checksum = fileMetadata.getChecksum();
        this.checksumAlgorithm = fileMetadata.getChecksumAlgorithm();
        this.bucketFileName = fileMetadata.getBucketFileName();
        this.fileExtension = fileMetadata.getFileExtension();
    }
}
