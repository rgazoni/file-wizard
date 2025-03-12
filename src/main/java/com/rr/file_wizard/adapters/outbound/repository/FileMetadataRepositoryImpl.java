package com.rr.file_wizard.adapters.outbound.repository;

import com.rr.file_wizard.adapters.outbound.entities.JpaFileMetadataEntity;
import com.rr.file_wizard.domain.filemetadata.FileMetadata;
import com.rr.file_wizard.domain.filemetadata.FileMetadataRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FileMetadataRepositoryImpl implements FileMetadataRepository {

    private final JpaFileMetadataRepository jpaFileMetadataRepository;

    public FileMetadataRepositoryImpl(JpaFileMetadataRepository jpaFileMetadataRepository) {
        this.jpaFileMetadataRepository = jpaFileMetadataRepository;
    }

    @Override
    public FileMetadata save(FileMetadata fileMetadata) {
        JpaFileMetadataEntity jpaFileMetadataEntity = new JpaFileMetadataEntity(fileMetadata);
        this.jpaFileMetadataRepository.save(jpaFileMetadataEntity);
        return new FileMetadata(jpaFileMetadataEntity.getId(), jpaFileMetadataEntity.getFileName(), jpaFileMetadataEntity.getContentType(), jpaFileMetadataEntity.getChecksum(), jpaFileMetadataEntity.getChecksumAlgorithm(), jpaFileMetadataEntity.getBucketFileName(), jpaFileMetadataEntity.getFileExtension());
    }

    @Override
    public List<FileMetadata> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.jpaFileMetadataRepository.findAll(pageable)
                .stream()
                .map(entity -> new FileMetadata(
                        entity.getId(),
                        entity.getFileName(),
                        entity.getContentType(),
                        entity.getChecksum(),
                        entity.getChecksumAlgorithm(),
                        entity.getBucketFileName(),
                        entity.getFileExtension()
                ))
                .collect(Collectors.toList());
    }
}
