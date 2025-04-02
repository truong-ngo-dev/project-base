package com.nob.app.core.exception;

/**
 * Exception thrown when a gateway or proxy does not receive a timely response
 * from an upstream server while acting as an intermediary.
 * This typically occurs when the upstream service is slow or unresponsive.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * Response response = externalService.call();
 * if (response.getStatusCode() == 504) {
 *     throw new GatewayTimeoutException("Upstream Timeout", "The upstream service did not respond in time.");
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#GATEWAY_TIMEOUT}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class GatewayTimeoutException extends ApplicationException {

    /**
     * Constructs a GatewayTimeoutException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the gateway timeout error occurred.
     * @param detail      Additional information related to the error (e.g., upstream response time).
     */
    public GatewayTimeoutException(String message, String description, Object detail) {
        super(message, ApplicationStatus.GATEWAY_TIMEOUT, description, detail);
    }

    /**
     * Constructs a GatewayTimeoutException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of why the gateway timeout error occurred.
     */
    public GatewayTimeoutException(String message, String description) {
        super(message, ApplicationStatus.GATEWAY_TIMEOUT, description);
    }

    /**
     * Constructs a GatewayTimeoutException with additional error details.
     *
     * @param detail Additional information related to the error
     *               (e.g., logs showing request timeouts).
     */
    public GatewayTimeoutException(Object detail) {
        super(ApplicationStatus.GATEWAY_TIMEOUT, detail);
    }

    /**
     * Constructs a default GatewayTimeoutException with no additional details.
     */
    public GatewayTimeoutException() {
        super(ApplicationStatus.GATEWAY_TIMEOUT);
    }
}
