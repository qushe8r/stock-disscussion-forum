package log.qushe8r.stockdiscussionforum.security.handler;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import log.qushe8r.stockdiscussionforum.security.jwt.JwtProcessor;
import log.qushe8r.stockdiscussionforum.security.redis.TokenService;
import log.qushe8r.stockdiscussionforum.security.utils.CookieCreator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtLogoutHandler implements LogoutSuccessHandler {
    private final HttpStatus httpStatus;
    private final JwtProcessor jwtProcessor;
    private final CookieCreator cookieCreator;
    private final TokenService tokenService;

    public JwtLogoutHandler(JwtProcessor jwtProcessor, CookieCreator cookieCreator, TokenService tokenService) {
        this.jwtProcessor = jwtProcessor;
        this.cookieCreator = cookieCreator;
        this.tokenService = tokenService;
        this.httpStatus = HttpStatus.OK;
    }

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        String accessToken =
                request.getHeader(HttpHeaders.AUTHORIZATION).substring(JwtProcessor.BEARER.length());
        Claims claims = jwtProcessor.extractClaims(accessToken);
        String jti = claims.getId();
        tokenService.deleteById(jti);
        Cookie cookie = cookieCreator.createExpired();
        response.addCookie(cookie);
        response.setStatus(this.httpStatus.value());
    }
}
