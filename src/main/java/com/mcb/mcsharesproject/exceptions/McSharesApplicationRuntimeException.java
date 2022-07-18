package com.mcb.mcsharesproject.exceptions;

import java.io.Serial;

/**
 * This class is used prevent thrown generic exception runtime .
 */
public class McSharesApplicationRuntimeException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 952543600337786660L;

    public McSharesApplicationRuntimeException() {
    }

    public McSharesApplicationRuntimeException(String message) {
        super(message);
    }

    public McSharesApplicationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public McSharesApplicationRuntimeException(Throwable cause) {
        super(cause);
    }

    public McSharesApplicationRuntimeException(String message,
                                               Throwable cause,
                                               boolean enableSuppression,
                                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
