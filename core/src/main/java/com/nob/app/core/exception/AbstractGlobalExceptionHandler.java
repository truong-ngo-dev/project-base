package com.nob.app.core.exception;

import com.nob.app.core.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract base class for handling global exceptions in the application.
 * This class extends {@link ResponseEntityExceptionHandler} and provides
 * centralized exception handling for common Spring MVC exceptions.
 *
 * <p>Each overridden method logs the exception and returns a standardized response
 * using {@link Response#wrap}.</p>
 * @author Truong Ngo
 * @version 1.0
 */
public abstract class AbstractGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(AbstractGlobalExceptionHandler.class);

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            @NonNull HttpRequestMethodNotSupportedException ex, 
            @NonNull HttpHeaders headers, 
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_REQUEST, "Method not supported");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            @NonNull HttpMediaTypeNotAcceptableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_REQUEST, "Method media type not acceptable");
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            @NonNull MissingPathVariableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_REQUEST, "Missing path variable");
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            @NonNull MissingServletRequestParameterException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_REQUEST, "Missing required request params");
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            @NonNull MissingServletRequestPartException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_REQUEST, "Missing required request part");
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            @NonNull ServletRequestBindingException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_DATA, "Request binding error");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_DATA, "Invalid method parameter");
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(
            @NonNull HandlerMethodValidationException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_DATA, "Method validation failed");
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            @NonNull NoHandlerFoundException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_REQUEST, "No handler found");
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
            @NonNull NoResourceFoundException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.RESOURCE_NOT_FOUND, "Resource not found");
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
            @NonNull AsyncRequestTimeoutException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INTERNAL_SERVER_ERROR, null, "Request timed out", "The request took too long to process");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            @NonNull HttpMediaTypeNotSupportedException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        String supportedTypes = ex.getSupportedMediaTypes().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        String description = supportedTypes.isEmpty()
                ? "The provided Content-Type is not supported"
                : "Supported media types: " + supportedTypes;
        return Response.wrap(ApplicationStatus.INVALID_REQUEST, null, "Unsupported media type", description);
    }


    @Override
    protected ResponseEntity<Object> handleMethodValidationException(
            @NonNull MethodValidationException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        List<String> errors = ex.getAllErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return Response.wrap(ApplicationStatus.VALIDATION_ERROR, errors, "Method validation failed", "One or more constraints were violated");
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NonNull HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_REQUEST, "Invalid request payload");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            @NonNull HttpMessageNotWritableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INTERNAL_SERVER_ERROR, "Error writing response");
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
            @NonNull ConversionNotSupportedException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INTERNAL_SERVER_ERROR, "Conversion not supported");
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            @NonNull TypeMismatchException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_DATA, "Type mismatch");
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestNotUsableException(
            @NonNull AsyncRequestNotUsableException ex, @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INTERNAL_SERVER_ERROR, "Async request not usable");
    }

    @Override
    protected ResponseEntity<Object> handleMaxUploadSizeExceededException(
            @NonNull MaxUploadSizeExceededException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INVALID_REQUEST, "File size exceeds limit");
    }

    @Override
    protected ResponseEntity<Object> handleErrorResponseException(
            @NonNull ErrorResponseException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        log.error(ex.getMessage());
        return Response.wrap(ApplicationStatus.INTERNAL_SERVER_ERROR, "Error processing request");
    }
}
