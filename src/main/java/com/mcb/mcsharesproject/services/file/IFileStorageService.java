package com.mcb.mcsharesproject.services.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface IFileStorageService {
    void init();
    void save(MultipartFile file);
    Resource load(String filename);
    void create(Path filePath);
}
