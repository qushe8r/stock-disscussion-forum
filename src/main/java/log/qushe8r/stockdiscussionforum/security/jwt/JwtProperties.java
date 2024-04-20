package log.qushe8r.stockdiscussionforum.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record JwtProperties(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.expiration.access}") Integer accessExpirationMinutes,
        @Value("${jwt.expiration.refresh}") Integer refreshExpirationMinutes
) {
}
