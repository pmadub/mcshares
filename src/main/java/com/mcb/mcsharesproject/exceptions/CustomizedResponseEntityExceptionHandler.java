package com.mcb.mcsharesproject.exceptions;

import com.mcb.mcsharesproject.entities.error.ErrorLog;
import com.mcb.mcsharesproject.repositories.error.ErrorLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * This responsibility of this class to handle all exceptions derived by controllers.
 * It extends the default exception handler class so the provide specific error handling
 * actions.
 */
@ControllerAdvice
@RestController
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorLogRepository errorLogRepository;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return new ResponseEntity<>(new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),ex.getBindingResult().toString()),
                                    HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public final void handleGlobalException(Exception ex, WebRequest request) throws McSharesApplicationRuntimeException {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setRequest(request.toString());
        errorLog.setTimestamp(LocalDateTime.now());
        errorLog.setExceptionMessage(ex.getMessage());
        errorLogRepository.save(errorLog);
        log.info(" Error has been logged and saved ");
        throw new McSharesApplicationRuntimeException(ex);
    }


}
