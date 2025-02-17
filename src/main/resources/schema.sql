CREATE TABLE IF NOT EXISTS fileMetadata (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    file_name VARCHAR(255) NOT NULL,
    content_type VARCHAR(255) NOT NULL,
    checksum BIGINT NOT NULL,
    checksum_algorithm VARCHAR(255) NOT NULL,
    bucket_file_name VARCHAR(255) NOT NULL
);