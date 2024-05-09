package log.qushe8r.stockdiscussionforum.security.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import log.qushe8r.stockdiscussionforum.common.dto.ErrorResponse;
import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;
import log.qushe8r.stockdiscussionforum.security.exception.JwtExceptionCode;
import log.qushe8r.stockdiscussionforum.security.filter.JwtVerificationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        JwtExceptionCode exceptionCode =
                (JwtExceptionCode) request.getAttribute(JwtVerificationFilter.EXCEPTION);
        if (exceptionCode == null) {
            exceptionCode = JwtExceptionCode.TOKEN_NOT_FOUND;
        }
        responseWithJson(response, exceptionCode);
    }

    private void responseWithJson(HttpServletResponse response, JwtExceptionCode exceptionCode)
            throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(exceptionCode.getStatus());
        response.getWriter().write(toJsonResponse(exceptionCode));
    }

    private String toJsonResponse(ExceptionCode exceptionCode) throws JsonProcessingException {
        ErrorResponse errorResponse = ErrorResponse.of(exceptionCode);
        return objectMapper.writeValueAsString(errorResponse);
    }
}
