package com.nob.app.core.exception;

/**
 * Exception thrown when a business rule is violated.
 * This typically occurs when an operation does not comply with predefined business logic.
 *
 * <p>Example usage:</p>
 * <blockquote><pre>
 * if (user.getBalance() < withdrawalAmount) {
 *     throw new BusinessRuleViolationException("Insufficient Balance", "The withdrawal amount exceeds the available balance.");
 * }
 * </pre></blockquote>
 *
 * <p>Default error status: {@link ApplicationStatus#BUSINESS_RULE_VIOLATION}</p>
 *
 * @author Truong Ngo
 * @version 1.0.0
 */
public class BusinessRuleViolationException extends ApplicationException {

    /**
     * Constructs a BusinessRuleViolationException with a message, description, and additional details.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of the business rule violation.
     * @param detail      Additional information related to the error (e.g., invalid business logic condition).
     */
    public BusinessRuleViolationException(String message, String description, Object detail) {
        super(message, ApplicationStatus.BUSINESS_RULE_VIOLATION, description, detail);
    }

    /**
     * Constructs a BusinessRuleViolationException with a message and description.
     *
     * @param message     A short error message describing the issue.
     * @param description A detailed description of the business rule violation.
     */
    public BusinessRuleViolationException(String message, String description) {
        super(message, ApplicationStatus.BUSINESS_RULE_VIOLATION, description);
    }

    /**
     * Constructs a BusinessRuleViolationException with additional error details.
     *
     * @param detail Additional information related to the error
     *               (e.g., specific rule that was violated).
     */
    public BusinessRuleViolationException(Object detail) {
        super(ApplicationStatus.BUSINESS_RULE_VIOLATION, detail);
    }

    /**
     * Constructs a default BusinessRuleViolationException with no additional details.
     */
    public BusinessRuleViolationException() {
        super(ApplicationStatus.BUSINESS_RULE_VIOLATION);
    }
}
