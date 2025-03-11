package com.rr.file_wizard.adapters.outbound.repository;

import com.rr.file_wizard.domain.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Integer> {
}
