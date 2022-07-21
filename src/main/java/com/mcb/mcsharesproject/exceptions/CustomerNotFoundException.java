package com.mcb.mcsharesproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * This exception class extends McSharesApplicationRuntimeException class, it sends to the client side
 * a 404 response withe error message.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends McSharesApplicationRuntimeException{
    @Serial
    private static final long serialVersionUID = -4377543631777655699L;

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
