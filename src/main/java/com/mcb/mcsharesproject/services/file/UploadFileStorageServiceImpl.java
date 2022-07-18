package com.mcb.mcsharesproject.services.file;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@Qualifier("uploadFileStorageBean")
public class UploadFileStorageServiceImpl extends AbstractFileStorageService {

    public UploadFileStorageServiceImpl() {
        super();
    }

    @Override
    protected Path filePath() {
        return Path.of("/uploads");
    }

}
