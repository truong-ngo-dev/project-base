package com.nob.app.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enum representing application status codes and their associated HTTP status.
 * This enum provides predefined statuses for API responses.
 * Each status includes a unique code, an HTTP status, a short message, and a detailed description.
 * <p>
 * Example usage:
 * {@code
 * ApplicationStatus status = ApplicationStatus.SUCCESS;
 * }
 * </p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
@Getter
public enum ApplicationStatus {

    /** Success response. */
    SUCCESS("000", "SUCCESS", HttpStatus.OK, "Success", "The operation was completed successfully"),

    /** Invalid request error. */
    INVALID_REQUEST("001", "INVALID_REQUEST", HttpStatus.BAD_REQUEST, "Invalid request", "The input data is not valid"),

    /** Validation error. */
    VALIDATION_ERROR("002", "VALIDATION_ERROR", HttpStatus.BAD_REQUEST, "Validation error", "One or more fields did not meet the required validation criteria"),

    /** Data error. */
    INVALID_DATA("003", "INVALID_DATA", HttpStatus.INTERNAL_SERVER_ERROR, "Data error", "The provided data is invalid"),

    /** Unauthorized access error. */
    UNAUTHORIZED("004", "UNAUTHORIZED", HttpStatus.UNAUTHORIZED, "Unauthorized", "User authentication is required or the token is invalid"),

    /** Forbidden access error. */
    FORBIDDEN("005", "FORBIDDEN", HttpStatus.FORBIDDEN, "Forbidden", "User does not have permission to access this resource"),

    /** Resource not found error. */
    RESOURCE_NOT_FOUND("006", "RESOURCE_NOT_FOUND", HttpStatus.NOT_FOUND, "Resource not found", "The requested resource does not exist or has been removed"),

    /** Conflict error. */
    CONFLICT_ERROR("007", "CONFLICT_ERROR", HttpStatus.CONFLICT, "Conflict error", "The resource already exists or changes cannot be applied"),

    /** Internal server error. */
    INTERNAL_SERVER_ERROR("008", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", "An unexpected error occurred, please try again later"),

    /** External server error. */
    EXTERNAL_SERVER_ERROR("009", "EXTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "External server error", "An error occurred while communicating with an external system"),

    /** Service unavailable error. */
    SERVICE_UNAVAILABLE("010", "SERVICE_UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE, "Service unavailable", "The system is undergoing maintenance or experiencing issues"),

    /** Bad gateway error. */
    BAD_GATEWAY("011", "BAD_GATEWAY", HttpStatus.BAD_GATEWAY, "Bad gateway", "The gateway received an invalid response from the upstream server"),

    /** Gateway timeout error. */
    GATEWAY_TIMEOUT("012", "GATEWAY_TIMEOUT", HttpStatus.GATEWAY_TIMEOUT, "Gateway timeout", "The gateway did not receive a response from the upstream server in time"),

    /** Business rule violation error. */
    BUSINESS_RULE_VIOLATION("013", "BUSINESS_RULE_VIOLATION", HttpStatus.NOT_ACCEPTABLE, "Business rule violation", "The request cannot be processed due to a business rule constraint");


    /** The status code representing the response category (e.g., success, error). */
    private final String status;

    /** A unique application-specific code identifying the status. */
    private final String code;

    /** The HTTP status associated with this application status. */
    private final HttpStatus httpStatus;

    /** A short message providing a brief description of the status. */
    private final String message;

    /** A detailed explanation of the status and its context. */
    private final String description;


    /**
     * Constructs an ApplicationStatus enum instance.
     *
     * @param status      the status code
     * @param code        the status identifier
     * @param httpStatus  the associated HTTP status
     * @param message     the short description of the status
     * @param description the detailed description of the status
     */
    ApplicationStatus(String status, String code, HttpStatus httpStatus, String message, String description) {
        this.status = status;
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
        this.description = description;
    }
}
