package com.mcb.mcsharesproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerRequestDataFailedValidationException extends McSharesApplicationRuntimeException{
    @Serial
    private static final long serialVersionUID = 526374004186444243L;

    public CustomerRequestDataFailedValidationException(String message) {
        super(message);
    }
}
