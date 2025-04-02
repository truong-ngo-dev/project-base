package com.nob.app.core.feign;

import com.nob.app.core.service.TokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

/**
 * Feign request interceptor that adds an Authorization header with a Bearer token.
 *
 * <p>This interceptor retrieves the JWT token from {@link TokenService} and attaches it
 * to outgoing Feign client requests if no Authorization header is already present.</p>
 * @author Truong Ngo
 * @version 1.0
 */
@Component
public class FeignInterceptor implements RequestInterceptor {

    /**
     * Adds the Authorization header to outgoing requests if not already present.
     *
     * @param requestTemplate the request template to modify.
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Collection<String> authzHeaders = requestTemplate.headers().get(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull(authzHeaders) && !authzHeaders.isEmpty()) return;
        Jwt jwt = TokenService.getJwtToken();
        if (Objects.isNull(jwt)) return;
        String token = jwt.getTokenValue();
        requestTemplate.header(HttpHeaders.AUTHORIZATION, TokenService.BEARER + " " + token);
    }
}
