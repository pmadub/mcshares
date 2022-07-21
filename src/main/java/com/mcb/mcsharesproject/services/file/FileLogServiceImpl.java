package com.mcb.mcsharesproject.services.file;

import com.mcb.mcsharesproject.entities.file.FileLog;
import com.mcb.mcsharesproject.repositories.file.FileLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
@Slf4j
public class FileLogServiceImpl implements FileLogService {

    @Resource
    private FileLogRepository fileLogRepository;

    @Override
    public void save(String fileName, String fileLocation, boolean containErrors) {
        FileLog fileLog = new FileLog();
        fileLog.setFileName(fileName);
        fileLog.setFileLocation(fileLocation);
        fileLog.setDateOfEntry(LocalDateTime.now());
        fileLog.setContainErrors(containErrors);

        fileLogRepository.save(fileLog);
        log.info(" File {} has been saved into the database ", fileName);
    }
}
