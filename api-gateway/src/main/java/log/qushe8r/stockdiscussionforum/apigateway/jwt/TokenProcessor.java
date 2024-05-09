package log.qushe8r.stockdiscussionforum.apigateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class TokenProcessor {

    public static final String BEARER = "Bearer ";

    private final TokenProperties tokenProperties;

    public Claims extractClaims(String accessToken) {
        SecretKey secretKey = getSecretKey();
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        String secret = tokenProperties.secret();
        byte[] base64EncodedSecret = Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(base64EncodedSecret);
    }

}
