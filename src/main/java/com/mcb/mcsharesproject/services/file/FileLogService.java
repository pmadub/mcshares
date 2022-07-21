package com.mcb.mcsharesproject.services.file;

public interface FileLogService {

    void save(String fileName, String fileLocation, boolean containErrors);
}
