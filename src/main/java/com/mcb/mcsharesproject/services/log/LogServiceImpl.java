package com.mcb.mcsharesproject.services.log;

import com.mcb.mcsharesproject.entities.error.ErrorLog;
import com.mcb.mcsharesproject.repositories.error.ErrorLogRepository;
import com.mcb.mcsharesproject.vo.LogInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private ErrorLogRepository errorLogRepository;

    @Override
    public void logError(LogInfoVO logInfo) {
        ErrorLog error = new ErrorLog();
        error.setTimestamp(logInfo.getTimestamp());
        error.setRequest(logInfo.getRequest());
        error.setFileName(logInfo.getFileName());
        error.setExceptionMessage(logInfo.getError());
        errorLogRepository.save(error);
        log.info(" Error has been saved ");
    }
}
