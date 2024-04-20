package log.qushe8r.stockdiscussionforum.security.utils;

import jakarta.servlet.http.Cookie;
import log.qushe8r.stockdiscussionforum.common.property.ApplicationProperties;
import log.qushe8r.stockdiscussionforum.security.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieCreator {
    public static final String REFRESH = "refresh";
    public static final String REISSUE = "/reissue";

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
}
