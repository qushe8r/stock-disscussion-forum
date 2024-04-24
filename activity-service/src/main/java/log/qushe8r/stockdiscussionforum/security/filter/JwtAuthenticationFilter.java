package log.qushe8r.stockdiscussionforum.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import log.qushe8r.stockdiscussionforum.security.dto.UsernamePassword;
import log.qushe8r.stockdiscussionforum.security.jwt.JwtProcessor;
import log.qushe8r.stockdiscussionforum.security.redis.TokenService;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import log.qushe8r.stockdiscussionforum.security.utils.CookieCreator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.UUID;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;
    private final JwtProcessor jwtProcessor;
    private final CookieCreator cookieCreator;
    private final TokenService tokenService;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        UsernamePassword usernamePassword = objectMapper.readValue(
                request.getInputStream(),
                UsernamePassword.class
        );
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                usernamePassword.username(),
                usernamePassword.password()
        );
        return getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authentication
    ) {
        AuthenticatedUser user = (AuthenticatedUser) authentication.getPrincipal();
        String jti = UUID.randomUUID().toString();
        String access = jwtProcessor.generateAccessToken(jti, user);
        String refresh = jwtProcessor.generateRefreshToken(jti, user);
        tokenService.save(jti, user.getUserId());
        Cookie cookie = cookieCreator.create(refresh);
        response.setHeader(HttpHeaders.AUTHORIZATION, JwtProcessor.BEARER + access);
        response.addCookie(cookie);
    }
}
