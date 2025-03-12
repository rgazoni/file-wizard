package com.rr.file_wizard.domain.filemetadata;

import java.util.List;

public interface FileMetadataRepository {
   FileMetadata save(FileMetadata fileMetadata);
   List<FileMetadata> findAll(int page, int size);
}
