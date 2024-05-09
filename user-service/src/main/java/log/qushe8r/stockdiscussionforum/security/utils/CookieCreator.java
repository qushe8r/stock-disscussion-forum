package log.qushe8r.stockdiscussionforum.security.utils;

import jakarta.servlet.http.Cookie;
import log.qushe8r.stockdiscussionforum.common.property.ApplicationProperties;
import log.qushe8r.stockdiscussionforum.security.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieCreator {
    public static final String REFRESH = "refresh";
    public static final String REISSUE = "/api/auth/reissue";

    private final ApplicationProperties applicationProperties;
    private final JwtProperties jwtProperties;

    public Cookie create(String refreshToken) {
        String domain = applicationProperties.domain();
        int maxAge = jwtProperties.refreshExpirationMinutes();

        Cookie cookie = new Cookie(REFRESH, refreshToken);
        cookie.setDomain(domain);
        cookie.setPath(REISSUE);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);

        return cookie;
    }

    public Cookie createExpired() {
        Cookie cookie = new Cookie(REFRESH, null);
        cookie.setDomain(null);
        cookie.setPath(REISSUE);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);

        return cookie;
    }

    public ResponseCookie createResponseCookie(String refreshToken) {
        String domain = applicationProperties.domain();
        int maxAge = jwtProperties.refreshExpirationMinutes();

        return ResponseCookie.from(REFRESH)
                .value(refreshToken)
                .domain(domain)
                .path(REISSUE)
                .maxAge(maxAge)
                .httpOnly(true)
                .secure(true)
                .build();
    }
}
