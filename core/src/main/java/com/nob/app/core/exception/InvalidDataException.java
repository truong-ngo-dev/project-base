package com.nob.app.core.exception;

/**
 * Exception thrown when the provided data is invalid.
 * This typically occurs when parsing JSON, handling malformed data,
 * or processing an unexpected data format.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * try {
 *     MyObject obj = objectMapper.readValue(jsonString, MyObject.class);
 * } catch (JsonProcessingException e) {
 *     throw new InvalidDataException("Invalid JSON", "Failed to parse JSON data.", e.getMessage());
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#INVALID_DATA}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class InvalidDataException extends ApplicationException {

    /**
     * Constructs an InvalidDataException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the data is invalid.
     * @param detail      Additional information that helps diagnose the error (e.g., invalid values, parsing errors).
     */
    public InvalidDataException(String message, String description, Object detail) {
        super(message, ApplicationStatus.INVALID_DATA, description, detail);
    }

    /**
     * Constructs an InvalidDataException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the data is invalid.
     */
    public InvalidDataException(String message, String description) {
        super(message, ApplicationStatus.INVALID_DATA, description);
    }

    /**
     * Constructs an InvalidDataException with additional error details.
     *
     * @param detail Additional information that helps diagnose the error
     *               (e.g., invalid input fields, parsing failures).
     */
    public InvalidDataException(Object detail) {
        super(ApplicationStatus.INVALID_DATA, detail);
    }

    /**
     * Constructs a default InvalidDataException with no additional details.
     */
    public InvalidDataException() {
        super(ApplicationStatus.INVALID_DATA);
    }
}
