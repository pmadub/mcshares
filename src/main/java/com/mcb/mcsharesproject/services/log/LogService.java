package com.mcb.mcsharesproject.services.log;

import com.mcb.mcsharesproject.vo.LogInfoVO;

public interface LogService {

    void logError(LogInfoVO logInfo);
}
