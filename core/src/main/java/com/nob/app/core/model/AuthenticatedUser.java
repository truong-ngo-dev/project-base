package com.nob.app.core.model;

import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * Represents an authenticated user extracted from a JWT token.
 *
 * <p>This class contains user details such as ID, username, roles, and additional attributes.</p>
 * @author Truong Ngo
 * @version 1.0
 */
@Data
public class AuthenticatedUser {

    /**
     * The unique identifier of the authenticated user.
     */
    private String id;

    /**
     * The username of the authenticated user.
     */
    private String username;

    /**
     * The roles assigned to the authenticated user.
     */
    private List<String> roles;

    /**
     * Additional attributes related to the authenticated user.
     */
    private Map<String, Object> attributes;

}
