package com.nob.app.core.exception;

/**
 * Exception thrown when a gateway or proxy receives an invalid response
 * from an upstream server while acting as an intermediary.
 * This typically happens when the upstream server returns an unexpected
 * or malformed response.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * Response response = externalService.call();
 * if (response.getStatusCode() == 502) {
 *     throw new BadGatewayException("Invalid Upstream Response", "The upstream service returned an error.");
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#BAD_GATEWAY}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class BadGatewayException extends ApplicationException {

    /**
     * Constructs a BadGatewayException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the bad gateway error occurred.
     * @param detail      Additional information related to the error (e.g., upstream response details).
     */
    public BadGatewayException(String message, String description, Object detail) {
        super(message, ApplicationStatus.BAD_GATEWAY, description, detail);
    }

    /**
     * Constructs a BadGatewayException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the bad gateway error occurred.
     */
    public BadGatewayException(String message, String description) {
        super(message, ApplicationStatus.BAD_GATEWAY, description);
    }

    /**
     * Constructs a BadGatewayException with additional error details.
     *
     * @param detail Additional information related to the error
     *               (e.g., raw response from upstream server).
     */
    public BadGatewayException(Object detail) {
        super(ApplicationStatus.BAD_GATEWAY, detail);
    }

    /**
     * Constructs a default BadGatewayException with no additional details.
     */
    public BadGatewayException() {
        super(ApplicationStatus.BAD_GATEWAY);
    }
}
