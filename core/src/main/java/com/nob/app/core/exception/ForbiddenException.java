package com.nob.app.core.exception;

/**
 * Exception thrown when a user is authenticated but does not have permission to access a resource or perform an action.
 * This typically occurs when authorization checks fail due to insufficient privileges.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * if (!user.hasRole("ADMIN")) {
 *     throw new ForbiddenException("Access Denied", "User does not have the required permissions.");
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#FORBIDDEN}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class ForbiddenException extends ApplicationException {

    /**
     * Constructs a ForbiddenException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why access is forbidden.
     * @param detail      Additional information related to the error (e.g., required roles).
     */
    public ForbiddenException(String message, String description, Object detail) {
        super(message, ApplicationStatus.FORBIDDEN, description, detail);
    }

    /**
     * Constructs a ForbiddenException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why access is forbidden.
     */
    public ForbiddenException(String message, String description) {
        super(message, ApplicationStatus.FORBIDDEN, description);
    }

    /**
     * Constructs a ForbiddenException with additional error details.
     *
     * @param detail Additional information related to the error (e.g., required permissions).
     */
    public ForbiddenException(Object detail) {
        super(ApplicationStatus.FORBIDDEN, detail);
    }

    /**
     * Constructs a default ForbiddenException with no additional details.
     */
    public ForbiddenException() {
        super(ApplicationStatus.FORBIDDEN);
    }
}
