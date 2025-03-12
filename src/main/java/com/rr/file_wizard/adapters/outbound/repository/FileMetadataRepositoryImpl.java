package com.rr.file_wizard.adapters.outbound.repository;

import com.rr.file_wizard.adapters.outbound.entities.JpaFileMetadataEntity;
import com.rr.file_wizard.domain.filemetadata.FileMetadata;
import com.rr.file_wizard.domain.filemetadata.FileMetadataRepository;
import com.rr.file_wizard.utils.mappers.FileMetadataMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FileMetadataRepositoryImpl implements FileMetadataRepository {

    private final JpaFileMetadataRepository jpaFileMetadataRepository;
    private final FileMetadataMapper fileMetadataMapper;

    public FileMetadataRepositoryImpl(JpaFileMetadataRepository jpaFileMetadataRepository, FileMetadataMapper fileMetadataMapper) {
        this.jpaFileMetadataRepository = jpaFileMetadataRepository;
        this.fileMetadataMapper = fileMetadataMapper;
    }

    @Override
    public FileMetadata save(FileMetadata fileMetadata) {
        JpaFileMetadataEntity jpaFileMetadataEntity = new JpaFileMetadataEntity(fileMetadata);
        this.jpaFileMetadataRepository.save(jpaFileMetadataEntity);
        return fileMetadataMapper.jpaToDomain(jpaFileMetadataEntity);
    }

    @Override
    public List<FileMetadata> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.jpaFileMetadataRepository.findAll(pageable)
                .stream()
                .map(fileMetadataMapper::jpaToDomain)
                .collect(Collectors.toList());
    }
}
