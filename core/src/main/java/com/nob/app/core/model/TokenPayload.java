package com.nob.app.core.model;

import lombok.Data;

import java.time.Instant;
import java.util.Map;


/**
 * Represents the payload of a JWT token.
 *
 * <p>This class contains details extracted from a JWT, including claims and metadata.</p>
 *
 * @author Truong Ngo
 * @version 1.0
 */
@Data
public class TokenPayload {

    /**
     * The unique identifier for the JWT.
     */
    private String jwtId;

    /**
     * The issuer of the JWT.
     */
    private String issuer;

    /**
     * The subject of the JWT (typically the user ID).
     */
    private String subject;

    /**
     * The audience for which the JWT is intended.
     */
    private String audience;

    /**
     * The expiration timestamp of the JWT.
     */
    private Instant expireAt;

    /**
     * The issued timestamp of the JWT.
     */
    private Instant issuedAt;

    /**
     * The "not before" timestamp indicating when the JWT becomes valid.
     */
    private Instant notBefore;

    /**
     * A map of all claims contained in the JWT.
     */
    private Map<String, Object> claims;

    /**
     * The type of the token (e.g., Bearer).
     */
    private String tokenType;

    /**
     * The raw JWT token value.
     */
    private String tokenValue;
}
