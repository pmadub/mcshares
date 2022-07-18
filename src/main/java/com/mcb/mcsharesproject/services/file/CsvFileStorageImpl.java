package com.mcb.mcsharesproject.services.file;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@Qualifier("csvFileStorageBean")
public class CsvFileStorageImpl extends AbstractFileStorageService{

    public CsvFileStorageImpl() {
        super();
    }

    @Override
    protected Path filePath() {
        return Path.of("csv");
    }

}
