package com.nob.app.core.exception;

/**
 * Exception thrown when a request is invalid.
 * This typically occurs when the request contains incorrect data,
 * missing required parameters, or violates API constraints.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * if (request.getParameter("id") == null) {
 *     throw new InvalidRequestException("Missing parameter", "The 'id' parameter is required.");
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#INVALID_REQUEST}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class InvalidRequestException extends ApplicationException {

    /**
     * Constructs an InvalidRequestException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the request is invalid.
     * @param detail      Additional information that helps diagnose the error (e.g., invalid values).
     */
    public InvalidRequestException(String message, String description, Object detail) {
        super(message, ApplicationStatus.INVALID_REQUEST, description, detail);
    }

    /**
     * Constructs an InvalidRequestException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the request is invalid.
     */
    public InvalidRequestException(String message, String description) {
        super(message, ApplicationStatus.INVALID_REQUEST, description);
    }

    /**
     * Constructs an InvalidRequestException with additional error details.
     *
     * @param detail Additional information that helps diagnose the error
     *               (e.g., invalid input fields).
     */
    public InvalidRequestException(Object detail) {
        super(ApplicationStatus.INVALID_REQUEST, detail);
    }

    /**
     * Constructs a default InvalidRequestException with no additional details.
     */
    public InvalidRequestException() {
        super(ApplicationStatus.INVALID_REQUEST);
    }
}
