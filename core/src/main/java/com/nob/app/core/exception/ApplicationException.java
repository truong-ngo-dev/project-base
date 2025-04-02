package com.nob.app.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract base class for application-specific exceptions.
 */
@Getter
@Setter
public abstract class ApplicationException extends RuntimeException {

    private ApplicationStatus status;
    private String description;
    private Object detail;

    /**
     * Constructs an ApplicationException with a message, status, description, and additional details.
     *
     * @param message     the exception message
     * @param status      the application status associated with the exception
     * @param description a detailed description of the exception
     * @param detail      additional details about the exception
     */
    protected ApplicationException(String message, ApplicationStatus status, String description, Object detail) {
        super(message);
        this.status = status;
        this.description = description;
        this.detail = detail;
    }

    /**
     * Constructs an ApplicationException with a message, status, and description.
     *
     * @param message     the exception message
     * @param status      the application status associated with the exception
     * @param description a detailed description of the exception
     */
    protected ApplicationException(String message, ApplicationStatus status, String description) {
        super(message);
        this.status = status;
        this.description = description;
        this.detail = null;
    }

    /**
     * Constructs an ApplicationException with a status and additional details.
     *
     * @param status the application status associated with the exception
     * @param detail additional details about the exception
     */
    protected ApplicationException(ApplicationStatus status, Object detail) {
        super(status.toString());
        this.status = status;
        this.description = status.getDescription();
        this.detail = detail;
    }

    /**
     * Constructs an ApplicationException with a status.
     *
     * @param status the application status associated with the exception
     */
    protected ApplicationException(ApplicationStatus status) {
        super(status.toString());
        this.status = status;
        this.description = status.getDescription();
        this.detail = null;
    }
}
