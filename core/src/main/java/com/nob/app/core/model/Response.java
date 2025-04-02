package com.nob.app.core.model;

import com.nob.app.core.constant.CustomHeaders;
import com.nob.app.core.exception.ApplicationException;
import com.nob.app.core.exception.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

/**
 * Generic response wrapper for REST API responses.
 * <p>
 * This class provides a standardized structure for API responses, including metadata
 * such as status, code, HTTP status, message, timestamps, request and trace IDs.
 * It also provides utility methods to create success and error responses.
 * </p>
 *
 * @param <D> the type of data in the response
 * @author Truong Ngo
 * @version 1.0
 */
@Getter
@Setter
public class Response<D> {

    /** The status of the response, indicating success or failure. */
    private String status;

    /** The application-specific code representing the response outcome. */
    private String code;

    /** The HTTP status code associated with the response. */
    private Integer httpStatus;

    /** A short message describing the response. */
    private String message;

    /** A detailed description providing additional information about the response. */
    private String description;

    /** The timestamp (epoch seconds) when the response was created. */
    private Long timestamp;

    /** The unique request ID for tracing requests. */
    private String requestId;

    /** The trace ID used for tracking requests across services. */
    private String traceId;

    /** The actual response data payload. */
    private D data;


    /**
     * Creates a response entity with the given application status, data, message, and description.
     *
     * @param status      the application status
     * @param data        the response data
     * @param message     the response message
     * @param description the response description
     * @param <D>         the type of data in the response
     * @return a ResponseEntity containing the standardized response
     */
    public static <D> ResponseEntity<Response<D>> of(ApplicationStatus status, D data, String message, String description) {
        Response<D> response = new Response<>();
        response.setHttpStatus(status.getHttpStatus().value());
        response.setStatus(status.getStatus());
        response.setCode(status.getCode());
        response.setMessage(message);
        response.setDescription(description);
        response.setTimestamp(Instant.now().getEpochSecond());
        response.setRequestId(MDC.get(CustomHeaders.REQUEST_ID));
        response.setTraceId(MDC.get(CustomHeaders.TRACE_ID));
        response.setData(data);
        return ResponseEntity.status(status.getHttpStatus()).body(response);
    }


    /**
     * Creates a response entity with the given application status and data.
     *
     * @param status the application status
     * @param data   the response data
     * @param <D>    the type of data in the response
     * @return a ResponseEntity containing the standardized response
     */
    public static <D> ResponseEntity<Response<D>> of(ApplicationStatus status, D data) {
        Response<D> response = new Response<>();
        response.setHttpStatus(status.getHttpStatus().value());
        response.setStatus(status.getStatus());
        response.setCode(status.getCode());
        response.setMessage(status.getMessage());
        response.setDescription(status.getDescription());
        response.setTimestamp(Instant.now().getEpochSecond());
        response.setRequestId(MDC.get(CustomHeaders.REQUEST_ID));
        response.setTraceId(MDC.get(CustomHeaders.TRACE_ID));
        response.setData(data);
        return ResponseEntity.status(status.getHttpStatus()).body(response);
    }


    /**
     * Creates an object type response entity with the given application status, data, message, and description.
     *
     * @param status      the application status
     * @param data        the response data
     * @param message     the response message
     * @param description the response description
     * @return a ResponseEntity containing the standardized response
     */
    public static ResponseEntity<Object> wrap(ApplicationStatus status, Object data, String message, String description) {
        ResponseEntity<Response<Object>> response = of(status, data, message, description);
        return ResponseEntity.status(status.getHttpStatus()).body(response);
    }


    /**
     * Creates an object type response entity with the given application status and data.
     *
     * @param status the application status
     * @param data   the response data as object
     * @return a ResponseEntity containing the standardized response
     */
    public static ResponseEntity<Object> wrap(ApplicationStatus status, Object data) {
        ResponseEntity<Response<Object>> response = of(status, data);
        return ResponseEntity.status(status.getHttpStatus()).body(response);
    }


    /**
     * Creates a success response entity with the given data, message, and description.
     *
     * @param data        the response data
     * @param message     the response message
     * @param description the response description
     * @param <D>         the type of data in the response
     * @return a ResponseEntity containing the success response
     */
    public static <D> ResponseEntity<Response<D>> success(D data, String message, String description) {
        return of(ApplicationStatus.SUCCESS, data, message, description);
    }


    /**
     * Creates a success response entity with the given data.
     *
     * @param data the response data
     * @param <D>  the type of data in the response
     * @return a ResponseEntity containing the success response
     */
    public static <D> ResponseEntity<Response<D>> success(D data) {
        return of(ApplicationStatus.SUCCESS, data);
    }


    /**
     * Creates an error response entity from an {@code ApplicationException}.
     *
     * @param exception the application exception
     * @return a ResponseEntity containing the error response
     */
    public static ResponseEntity<Response<Object>> error(ApplicationException exception) {
        return of(exception.getStatus(), exception.getDetail(), exception.getMessage(), exception.getDescription());
    }



    /**
     * Creates an object base error response entity from an {@code ApplicationException}.
     *
     * @param exception the application exception
     * @return a ResponseEntity containing the error response
     */
    public static ResponseEntity<Object> wrap(ApplicationException exception) {
        ResponseEntity<Response<Object>> response = error(exception);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
