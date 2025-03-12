package com.rr.file_wizard.adapters.outbound.repository;

import com.rr.file_wizard.adapters.outbound.entities.JpaFileMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFileMetadataRepository extends JpaRepository<JpaFileMetadataEntity, Integer> {
}
