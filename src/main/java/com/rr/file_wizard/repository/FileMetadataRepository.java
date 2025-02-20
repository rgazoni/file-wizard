package com.rr.file_wizard.repository;

import com.rr.file_wizard.model.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Integer> {
}
