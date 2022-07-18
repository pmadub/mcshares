package com.mcb.mcsharesproject.services.file;

import com.mcb.mcsharesproject.exceptions.FileOperationFailureException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractFileStorageService implements IFileStorageService {

    protected abstract Path filePath();

    protected AbstractFileStorageService() {
        init();
    }

    @Override
    public void init() {
        try {
            if(!Files.exists(filePath())) {
                Files.createDirectory(filePath());
            }

        } catch (IOException e) {
            throw new FileOperationFailureException("Could not initialize folder for upload!", e);
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.deleteIfExists(this.filePath().resolve(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), this.filePath().resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new FileOperationFailureException("Could not store the file. ",e);
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = filePath().resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileOperationFailureException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new FileOperationFailureException("MalformedURLException: ",e);
        }
    }

    @Override
    public void create(Path filePath) {
        try {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
        } catch (Exception e) {
            throw new FileOperationFailureException("Could not create the file. Error: ",e);
        }
    }
}
