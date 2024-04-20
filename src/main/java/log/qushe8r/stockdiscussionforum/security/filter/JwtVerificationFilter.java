package log.qushe8r.stockdiscussionforum.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import log.qushe8r.stockdiscussionforum.security.exception.JwtExceptionCode;
import log.qushe8r.stockdiscussionforum.security.jwt.JwtProcessor;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtVerificationFilter extends OncePerRequestFilter {
    public static final String EXCEPTION = "exception";
    private final JwtProcessor jwtProcessor;

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        String bearerToken = getAuthorizationHeaderValue(request);
        return bearerToken == null || !bearerToken.startsWith(JwtProcessor.BEARER);
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            setAuthenticationToContext(request);
        } catch (ExpiredJwtException e) {
            request.setAttribute(EXCEPTION, JwtExceptionCode.ACCESS_TOKEN_EXPIRED);
        } catch (SignatureException e) {
            request.setAttribute(EXCEPTION, JwtExceptionCode.INVALID_TOKEN);
        } catch (JwtException e) {
            log.warn("JwtException: {}", e.getClass());
            request.setAttribute(EXCEPTION, JwtExceptionCode.INVALID_TOKEN);
        }
        filterChain.doFilter(request, response);

    }

    private void setAuthenticationToContext(HttpServletRequest request) {
        Authentication authentication = createAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Authentication createAuthentication(HttpServletRequest request) {
        String accessToken =
                getAuthorizationHeaderValue(request).substring(JwtProcessor.BEARER.length());
        Claims claims = jwtProcessor.extractClaims(accessToken);
        AuthenticatedUser memberDetails = AuthenticatedUser.toAuthenticatedUser(claims);
        return new UsernamePasswordAuthenticationToken(
                memberDetails, memberDetails.getPassword(), memberDetails.getAuthorities());
    }

    private String getAuthorizationHeaderValue(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
