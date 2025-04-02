package com.nob.app.core.service;

import com.nob.app.core.model.AuthenticatedUser;
import com.nob.app.core.model.TokenPayload;
import com.nob.utils.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.function.Function;

/**
 * A class for extracting token-related information from the security context.
 * <p>This class provides methods to parse the JWT token and extract details about the authenticated user.</p>
 *
 * @author Truong Ngo
 * @version 1.0
 */
public class TokenService {

    /**
     * The claim key for the preferred username.
     */
    public static final String PREFERRED_USERNAME = "preferred_username";

    /**
     * The Bearer token type identifier.
     */
    public static final String BEARER = "Bearer";


    /**
     * Retrieves the JWT token from the security context.
     *
     * <p>This method extracts the JWT token associated with the current authentication context.</p>
     * @return {@link Jwt} representing the current authentication token, or {@code null} if no token is found.
     */
    public static Jwt getJwtToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return null;
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        return token.getToken();
    }


    /**
     * Parses the JWT token from the security context and extracts relevant details.
     *
     * @return {@link TokenPayload} containing token details, or {@code null} if no valid token is found.
     */
    public static TokenPayload getTokenPayload() {
        Jwt jwt = getJwtToken();
        if (jwt == null) return null;
        TokenPayload payload = new TokenPayload();
        payload.setJwtId(jwt.getId());
        payload.setIssuer(jwt.getIssuer().toString());
        payload.setSubject(jwt.getSubject());
        payload.setAudience(StringUtils.join(jwt.getAudience(), ",", Function.identity()));
        payload.setExpireAt(jwt.getExpiresAt());
        payload.setIssuedAt(jwt.getIssuedAt());
        payload.setNotBefore(jwt.getNotBefore());
        payload.setClaims(jwt.getClaims());

        payload.setTokenType(jwt.getClaimAsString("typ") == null ?
                TokenService.BEARER :
                jwt.getClaimAsString("typ"));
        payload.setTokenValue(jwt.getTokenValue());
        return payload;
    }


    /**
     * Retrieves the currently authenticated user from the security context.
     *
     * @return {@link AuthenticatedUser} containing user details, or {@code null} if no valid user is found.
     */
    public static AuthenticatedUser getLoggedInUser() {
        Jwt jwt = getJwtToken();
        if (jwt == null) return null;
        AuthenticatedUser user = new AuthenticatedUser();
        user.setId(jwt.getSubject());
        user.setUsername(jwt.getClaimAsString(PREFERRED_USERNAME));
        user.setAttributes(jwt.getClaims());
        return user;
    }
}
