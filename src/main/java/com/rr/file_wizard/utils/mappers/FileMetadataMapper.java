package com.rr.file_wizard.utils.mappers;

import com.rr.file_wizard.adapters.outbound.entities.JpaFileMetadataEntity;
import com.rr.file_wizard.domain.filemetadata.FileMetadata;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMetadataMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "fileName", target = "fileName")
    @Mapping(source = "contentType", target = "contentType")
    @Mapping(source = "checksum", target = "checksum")
    @Mapping(source = "checksumAlgorithm", target = "checksumAlgorithm")
    @Mapping(source = "bucketFileName", target = "bucketFileName")
    @Mapping(source = "fileExtension", target = "fileExtension")
    FileMetadata jpaToDomain(JpaFileMetadataEntity entity);
}