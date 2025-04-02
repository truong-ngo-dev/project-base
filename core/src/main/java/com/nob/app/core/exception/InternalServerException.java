package com.nob.app.core.exception;

/**
 * Exception thrown when an unexpected server error occurs.
 * This typically happens due to an unhandled exception, system failure,
 * or an issue that prevents the server from fulfilling the request.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * try {
 *     processRequest();
 * } catch (Exception e) {
 *     throw new InternalServerException("Unexpected Error", "An error occurred while processing the request.", e);
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#INTERNAL_SERVER_ERROR}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class InternalServerException extends ApplicationException {

    /**
     * Constructs an InternalServerException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the server error occurred.
     * @param detail      Additional information related to the error (e.g., exception details).
     */
    public InternalServerException(String message, String description, Object detail) {
        super(message, ApplicationStatus.INTERNAL_SERVER_ERROR, description, detail);
    }

    /**
     * Constructs an InternalServerException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the server error occurred.
     */
    public InternalServerException(String message, String description) {
        super(message, ApplicationStatus.INTERNAL_SERVER_ERROR, description);
    }

    /**
     * Constructs an InternalServerException with additional error details.
     *
     * @param detail Additional information related to the error
     *               (e.g., exception stack trace or system state).
     */
    public InternalServerException(Object detail) {
        super(ApplicationStatus.INTERNAL_SERVER_ERROR, detail);
    }

    /**
     * Constructs a default InternalServerException with no additional details.
     */
    public InternalServerException() {
        super(ApplicationStatus.INTERNAL_SERVER_ERROR);
    }
}
