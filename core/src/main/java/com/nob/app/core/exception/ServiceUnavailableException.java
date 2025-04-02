package com.nob.app.core.exception;

/**
 * Exception thrown when a service is temporarily unavailable.
 * This typically occurs due to server overload, maintenance, or temporary failures.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * boolean isUnderMaintenance = systemStatusService.isUnderMaintenance();
 * if (isUnderMaintenance) {
 *     throw new ServiceUnavailableException("Service Maintenance", "The system is currently under maintenance. Please try again later.");
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#SERVICE_UNAVAILABLE}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class ServiceUnavailableException extends ApplicationException {

    /**
     * Constructs a ServiceUnavailableException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the service is unavailable.
     * @param detail      Additional information related to the error (e.g., expected recovery time).
     */
    public ServiceUnavailableException(String message, String description, Object detail) {
        super(message, ApplicationStatus.SERVICE_UNAVAILABLE, description, detail);
    }

    /**
     * Constructs a ServiceUnavailableException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the service is unavailable.
     */
    public ServiceUnavailableException(String message, String description) {
        super(message, ApplicationStatus.SERVICE_UNAVAILABLE, description);
    }

    /**
     * Constructs a ServiceUnavailableException with additional error details.
     *
     * @param detail Additional information related to the error
     *               (e.g., estimated downtime duration).
     */
    public ServiceUnavailableException(Object detail) {
        super(ApplicationStatus.SERVICE_UNAVAILABLE, detail);
    }

    /**
     * Constructs a default ServiceUnavailableException with no additional details.
     */
    public ServiceUnavailableException() {
        super(ApplicationStatus.SERVICE_UNAVAILABLE);
    }
}
