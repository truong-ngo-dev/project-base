package com.nob.app.core.exception;

import com.nob.app.core.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler that captures application-specific exceptions
 * and maps them to appropriate {@link ApplicationStatus} responses.
 *
 * <p>This class extends {@link AbstractGlobalExceptionHandler} to inherit
 * common Spring MVC exception handling and adds custom exception handling
 * for application-specific errors.</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends AbstractGlobalExceptionHandler {

    /**
     * Handles {@link ApplicationException} and returns an appropriate response.
     *
     * @param ex the ApplicationException
     * @return a response entity with the corresponding status and message
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
        log.error(ex.getMessage(), ex);
        return Response.wrap(ex);
    }

}
