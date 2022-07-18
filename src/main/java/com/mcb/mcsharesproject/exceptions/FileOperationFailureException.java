package com.mcb.mcsharesproject.exceptions;

import java.io.Serial;

/**
 * This class represent exception occurred during file operation.
 */
public class FileOperationFailureException extends McSharesApplicationRuntimeException {

    @Serial
    private static final long serialVersionUID = -1398932187698409643L;

    public FileOperationFailureException(String message) {
        super(message);
    }

    public FileOperationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
