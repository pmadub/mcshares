package com.mcb.mcsharesproject.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ExceptionResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 7094039366194807531L;
    private LocalDateTime timestamp;
    private String message;
    private String detailedMessage;

    public ExceptionResponse(LocalDateTime timestamp, String message, String detailedMessage) {
        this.timestamp = timestamp;
        this.message = message;
        this.detailedMessage = detailedMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }
}
