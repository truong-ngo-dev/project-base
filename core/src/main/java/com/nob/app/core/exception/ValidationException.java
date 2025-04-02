package com.nob.app.core.exception;

/**
 * Exception thrown when a validation error occurs.
 * This is typically used when input data is invalid, such as missing required fields,
 * incorrect formats, or business rule violations.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * if (user.getEmail() == null) {
 *     throw new ValidationException("Email cannot be null", "The email field is required.");
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#VALIDATION_ERROR}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class ValidationException extends ApplicationException {

    /**
     * Constructs a ValidationException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of the validation error.
     * @param detail      Additional information that helps diagnose the error (e.g., object, list).
     */
    public ValidationException(String message, String description, Object detail) {
        super(message, ApplicationStatus.VALIDATION_ERROR, description, detail);
    }

    /**
     * Constructs a ValidationException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of the validation error.
     */
    public ValidationException(String message, String description) {
        super(message, ApplicationStatus.VALIDATION_ERROR, description);
    }

    /**
     * Constructs a ValidationException with additional error details.
     *
     * @param detail Additional information that helps diagnose the error
     *               (e.g., a list of validation errors).
     */
    public ValidationException(Object detail) {
        super(ApplicationStatus.VALIDATION_ERROR, detail);
    }

    /**
     * Constructs a default ValidationException with no additional details.
     */
    public ValidationException() {
        super(ApplicationStatus.VALIDATION_ERROR);
    }
}
