package com.nob.app.core.config;

import com.nob.app.core.constant.CustomHeaders;
import com.nob.app.core.model.ServiceHeader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.nob.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Intercepts HTTP requests and responses for logging and monitoring purposes.
 * <p>
 * This interceptor logs request details, extracts and stores service headers,
 * and ensures traceability by propagating request and trace IDs.
 * Additionally, it logs request and response bodies (except for binary data).
 * </p>
 *
 * @author Truong Ngo
 * @version 1.0
 */
@Component
@RestControllerAdvice
public class HttpMonitoringInterceptor extends RequestBodyAdviceAdapter implements HandlerInterceptor, ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(HttpMonitoringInterceptor.class);

    private static final String SERVICE_HEADER = "serviceHeader";

    private static final String LOG_TYPE = "logType";


    /**
     * Handles pre-processing of incoming HTTP requests.
     * Extracts service headers and logs request details.
     *
     * @param request  the incoming HTTP request
     * @param response the HTTP response
     * @param handler  the handler processing the request
     * @return {@code true} to proceed with request processing
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        ServiceHeader header = ServiceHeader.of(request);
        request.setAttribute(SERVICE_HEADER, header);
        MDC.put(SERVICE_HEADER, header.toString());
        MDC.put(CustomHeaders.REQUEST_ID, header.getRequestId());
        MDC.put(CustomHeaders.TRACE_ID, header.getTraceId());
        MDC.put(LOG_TYPE, LogType.REQUEST.name());
        log.info(
                "Receive request with uri: {}",
                Objects.nonNull(request.getQueryString()) ?
                        request.getRequestURI() + "?" + request.getQueryString() :
                        request.getRequestURI());
        return true;
    }


    /**
     * Logs the request body after it has been read.
     *
     * @param body            the deserialized request body
     * @param inputMessage    the input message
     * @param parameter       the method parameter
     * @param targetType      the target type of the request body
     * @param converterType   the message converter used
     * @return the original request body
     */
    @NonNull
    @Override
    public Object afterBodyRead(@NonNull Object body, @NonNull HttpInputMessage inputMessage, @NonNull MethodParameter parameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        String json = JsonUtils.toJson(body);
        log.info("Request body: {}", json);
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }


    /**
     * Handles post-processing after request completion.
     * Adds request and trace IDs to the response headers.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @param handler  the handler processing the request
     * @param ex       the exception, if any occurred
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        ServiceHeader header = JsonUtils.fromJson(MDC.get(SERVICE_HEADER), ServiceHeader.class);
        response.addHeader(CustomHeaders.REQUEST_ID, header.getRequestId());
        response.addHeader(CustomHeaders.TRACE_ID, header.getTraceId());
        log.info("Return response with status: {}", response.getStatus());
    }


    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }


    /**
     * Logs response bodies before they are sent to the client.
     *
     * @param body                the response body
     * @param returnType          the return type
     * @param selectedContentType the selected media type
     * @param selectedConverterType the selected converter type
     * @param request             the server request
     * @param response            the server response
     * @return the original response body
     */
    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType, @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (Objects.nonNull(body)) {
            if (body instanceof InputStreamResource || body instanceof ByteArrayInputStream || body instanceof byte[]) {
                return body;
            }
            String json = JsonUtils.toJson(JsonUtils.sanitizeByteArray(body));
            MDC.remove(LOG_TYPE);
            MDC.put(LOG_TYPE, LogType.RESPONSE.name());
            log.info("Response body: {}", json);
            return body;
        }
        return null;
    }


    /**
     * Enum representing log types (REQUEST and RESPONSE).
     */
    public enum LogType {
        REQUEST,
        RESPONSE
    }
}
