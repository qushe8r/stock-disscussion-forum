package log.qushe8r.stockdiscussionforum.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import log.qushe8r.stockdiscussionforum.common.dto.ErrorResponse;
import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;
import log.qushe8r.stockdiscussionforum.security.exception.AuthExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {
        ExceptionCode exceptionCode = AuthExceptionCode.INFORMATION_NOT_MATCHED;
        ErrorResponse errorResponse = ErrorResponse.of(exceptionCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(exceptionCode.getStatus());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
