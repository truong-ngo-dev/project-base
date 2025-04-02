package com.nob.app.core.exception;

/**
 * Exception thrown when a user is not authorized to access a resource or perform an action.
 * This typically occurs when authentication is missing, invalid, or expired.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * if (!userSession.isAuthenticated()) {
 *     throw new UnauthorizedException("Access Denied", "User must be authenticated to access this resource.");
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#UNAUTHORIZED}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class UnauthorizedException extends ApplicationException {

    /**
     * Constructs an UnauthorizedException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why access is unauthorized.
     * @param detail      Additional information related to the error (e.g., missing credentials).
     */
    public UnauthorizedException(String message, String description, Object detail) {
        super(message, ApplicationStatus.UNAUTHORIZED, description, detail);
    }

    /**
     * Constructs an UnauthorizedException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why access is unauthorized.
     */
    public UnauthorizedException(String message, String description) {
        super(message, ApplicationStatus.UNAUTHORIZED, description);
    }

    /**
     * Constructs an UnauthorizedException with additional error details.
     *
     * @param detail Additional information related to the error (e.g., missing token, expired session).
     */
    public UnauthorizedException(Object detail) {
        super(ApplicationStatus.UNAUTHORIZED, detail);
    }

    /**
     * Constructs a default UnauthorizedException with no additional details.
     */
    public UnauthorizedException() {
        super(ApplicationStatus.UNAUTHORIZED);
    }
}
