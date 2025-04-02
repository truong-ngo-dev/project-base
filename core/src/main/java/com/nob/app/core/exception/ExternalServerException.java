package com.nob.app.core.exception;

/**
 * Exception thrown when an error occurs while communicating with an external service.
 * This typically happens when an upstream API, third-party service, or external system
 * returns an error or becomes unavailable.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * try {
 *     externalService.call();
 * } catch (Exception e) {
 *     throw new ExternalServerException("External Service Failure", "The payment gateway is currently unavailable.", e);
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#EXTERNAL_SERVER_ERROR}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class ExternalServerException extends ApplicationException {

    /**
     * Constructs an ExternalServerException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the external server error occurred.
     * @param detail      Additional information related to the error (e.g., API response, exception details).
     */
    public ExternalServerException(String message, String description, Object detail) {
        super(message, ApplicationStatus.EXTERNAL_SERVER_ERROR, description, detail);
    }

    /**
     * Constructs an ExternalServerException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the external server error occurred.
     */
    public ExternalServerException(String message, String description) {
        super(message, ApplicationStatus.EXTERNAL_SERVER_ERROR, description);
    }

    /**
     * Constructs an ExternalServerException with additional error details.
     *
     * @param detail Additional information related to the error
     *               (e.g., external API response, failure logs).
     */
    public ExternalServerException(Object detail) {
        super(ApplicationStatus.EXTERNAL_SERVER_ERROR, detail);
    }

    /**
     * Constructs a default ExternalServerException with no additional details.
     */
    public ExternalServerException() {
        super(ApplicationStatus.EXTERNAL_SERVER_ERROR);
    }
}
