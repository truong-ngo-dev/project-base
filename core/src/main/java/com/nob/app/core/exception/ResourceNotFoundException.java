package com.nob.app.core.exception;

/**
 * Exception thrown when a requested resource is not found.
 * This typically occurs when trying to access a resource that does not exist
 * or has been removed from the system.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * Optional<User> user = userRepository.findById(userId);
 * if (user.isEmpty()) {
 *     throw new ResourceNotFoundException("User Not Found", "No user found with ID: " + userId);
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#RESOURCE_NOT_FOUND}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class ResourceNotFoundException extends ApplicationException {

    /**
     * Constructs a ResourceNotFoundException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the resource was not found.
     * @param detail      Additional information related to the error (e.g., resource type, identifier).
     */
    public ResourceNotFoundException(String message, String description, Object detail) {
        super(message, ApplicationStatus.RESOURCE_NOT_FOUND, description, detail);
    }

    /**
     * Constructs a ResourceNotFoundException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the resource was not found.
     */
    public ResourceNotFoundException(String message, String description) {
        super(message, ApplicationStatus.RESOURCE_NOT_FOUND, description);
    }

    /**
     * Constructs a ResourceNotFoundException with additional error details.
     *
     * @param detail Additional information related to the error
     *               (e.g., missing resource identifier).
     */
    public ResourceNotFoundException(Object detail) {
        super(ApplicationStatus.RESOURCE_NOT_FOUND, detail);
    }

    /**
     * Constructs a default ResourceNotFoundException with no additional details.
     */
    public ResourceNotFoundException() {
        super(ApplicationStatus.RESOURCE_NOT_FOUND);
    }
}

