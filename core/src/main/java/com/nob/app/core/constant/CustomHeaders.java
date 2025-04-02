package com.nob.app.core.constant;

import com.nob.utils.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


/**
 * Utility class for managing custom HTTP headers.
 *
 * <p>This class defines standard custom headers used in requests and responses
 * and provides utility methods to configure allowed headers.</p>
 *
 * @author Truong Ngo
 * @version 1.0
 */
public class CustomHeaders {

    /**
     * The request ID header name.
     */
    public static final String REQUEST_ID = "X-Request-Id";

    /**
     * The trace ID header name.
     */
    public static final String TRACE_ID = "X-Trace-Id";

    /**
     * List of allowed custom headers.
     */
    public static final List<String> ALLOWED_HEADERS = new ArrayList<>();

    static {
        ALLOWED_HEADERS.add(REQUEST_ID);
        ALLOWED_HEADERS.add(TRACE_ID);
    }

    /**
     * Sets the allowed custom headers in the response.
     *
     * @param response the HTTP servlet response to modify.
     */
    public static void setAllowedHeader(HttpServletResponse response) {
        response.setHeader(
                HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
                StringUtils.join(ALLOWED_HEADERS, ", ", Function.identity()));
    }
}
