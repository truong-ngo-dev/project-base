package com.nob.app.core.exception;

/**
 * Exception thrown when a conflict occurs while processing a request.
 * This typically happens when attempting to create a resource that already exists
 * or modifying a resource in a way that causes a conflict.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * if (userRepository.existsByEmail(email)) {
 *     throw new ConflictException("Email Already Registered", "A user with this email already exists.");
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#CONFLICT_ERROR}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class ConflictException extends ApplicationException {

    /**
     * Constructs a ConflictException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the conflict occurred.
     * @param detail      Additional information related to the error (e.g., conflicting resource).
     */
    public ConflictException(String message, String description, Object detail) {
        super(message, ApplicationStatus.CONFLICT_ERROR, description, detail);
    }

    /**
     * Constructs a ConflictException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the conflict occurred.
     */
    public ConflictException(String message, String description) {
        super(message, ApplicationStatus.CONFLICT_ERROR, description);
    }

    /**
     * Constructs a ConflictException with additional error details.
     *
     * @param detail Additional information related to the error
     *               (e.g., conflicting data or resource).
     */
    public ConflictException(Object detail) {
        super(ApplicationStatus.CONFLICT_ERROR, detail);
    }

    /**
     * Constructs a default ConflictException with no additional details.
     */
    public ConflictException() {
        super(ApplicationStatus.CONFLICT_ERROR);
    }
}
