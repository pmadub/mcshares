package com.mcb.mcsharesproject.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogInfoVO {
    private LocalDateTime timestamp;
    private String fileName;
    private String error;
    private String request;

    public LogInfoVO(LocalDateTime timestamp, String fileName, String error, String request) {
        this.timestamp = timestamp;
        this.fileName = fileName;
        this.error = error;
        this.request = request;
    }

    public LogInfoVO(LocalDateTime timestamp, String fileName, String error) {
        this.timestamp = timestamp;
        this.fileName = fileName;
        this.error = error;
    }


}
