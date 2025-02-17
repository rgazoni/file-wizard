package com.rr.file_wizard.repository;

import com.rr.file_wizard.model.FileMetadata;
import org.springframework.data.repository.CrudRepository;

public interface FileMetadataRepository extends CrudRepository<FileMetadata, Integer> {
}
