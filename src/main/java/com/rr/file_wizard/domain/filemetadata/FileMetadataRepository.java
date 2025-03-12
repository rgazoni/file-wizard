package com.rr.file_wizard.domain.filemetadata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FileMetadataRepository {
   FileMetadata save(FileMetadata fileMetadata);
   List<FileMetadata> findAll(int page, int size);
}
