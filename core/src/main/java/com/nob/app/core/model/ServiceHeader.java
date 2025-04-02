package com.nob.app.core.model;

import com.nob.app.core.constant.CustomHeaders;
import com.nob.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents the service header containing metadata about an incoming request.
 * This includes request context information, such as the request URI, HTTP method,
 * source and destination IP addresses, ports, and tracing identifiers.
 *
 * <p>This class provides a factory method {@link #of(HttpServletRequest)} to construct
 * a {@code ServiceHeader} instance from an {@code HttpServletRequest}.</p>
 * @author Truong Ngo
 * @version 1.0
 */
@Setter
@Getter
@Data
public class ServiceHeader {

    /** The context path of the request. */
    private String contextPath;

    /** The request URI. */
    private String uri;

    /** The HTTP method of the request. */
    private String httpMethod;

    /** The unique identifier for the request. */
    private String requestId;

    /** The unique trace identifier for tracking requests across services. */
    private String traceId;

    /** The timestamp of the request. */
    private Long timestamp;

    /** The IP address of the source application making the request. */
    private String srcAppIp;

    /** The port of the source application making the request. */
    private int srcAppPort;

    /** The IP address of the destination application receiving the request. */
    private String destAppIp;

    /** The port of the destination application receiving the request. */
    private int destAppPort;

    /** The authorization token, masked for security purposes. */
    private String authorization;


    /**
     * Default constructor.
     */
    public ServiceHeader() {}


    /**
     * Creates a {@code ServiceHeader} instance from the given {@code HttpServletRequest}.
     *
     * <p>Generates a new {@code requestId} and {@code traceId} if they are not present in the request headers.
     * The authorization token is masked for security reasons.</p>
     *
     * @param request the HTTP servlet request
     * @return a populated {@code ServiceHeader} instance
     */
    public static ServiceHeader of(HttpServletRequest request) {
        String requestId = Objects.nonNull(request.getHeader(CustomHeaders.REQUEST_ID)) ?
                request.getHeader(CustomHeaders.REQUEST_ID) :
                UUID.randomUUID().toString();
        String traceId = Objects.nonNull(request.getHeader(CustomHeaders.TRACE_ID)) ?
                request.getHeader(CustomHeaders.TRACE_ID) :
                UUID.randomUUID().toString();
        String authorization = Objects.nonNull(request.getHeader(HttpHeaders.AUTHORIZATION)) ?
                "<<Not intent to log>>" : null;
        Object uri = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        ServiceHeader header = new ServiceHeader();
        header.setContextPath(request.getContextPath());
        header.setUri(uri != null ? uri.toString() : "/");
        header.setSrcAppIp(request.getRemoteAddr());
        header.setDestAppIp(request.getLocalAddr());
        header.setSrcAppPort(request.getRemotePort());
        header.setDestAppPort(request.getLocalPort());
        header.setHttpMethod(request.getMethod());
        header.setRequestId(requestId);
        header.setTraceId(traceId);
        header.setAuthorization(authorization);
        return header;
    }


    /**
     * Returns a JSON representation of this {@code ServiceHeader}.
     *
     * @return a JSON string representing this object
     */
    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}

