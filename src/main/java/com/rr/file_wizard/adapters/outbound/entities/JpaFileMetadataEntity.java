package com.rr.file_wizard.adapters.outbound.entities;

import com.rr.file_wizard.domain.filemetadata.FileMetadata;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    public JpaFileMetadataEntity(FileMetadata fileMetadata) {
        this.fileName = fileMetadata.getFileName();
        this.contentType = fileMetadata.getContentType();
        this.checksum = fileMetadata.getChecksum();
        this.checksumAlgorithm = fileMetadata.getChecksumAlgorithm();
        this.bucketFileName = fileMetadata.getBucketFileName();
        this.fileExtension = fileMetadata.getFileExtension();
    }
}
