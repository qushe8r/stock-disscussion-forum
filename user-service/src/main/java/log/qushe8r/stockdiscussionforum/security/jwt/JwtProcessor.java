package log.qushe8r.stockdiscussionforum.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtProcessor {
    public static final String BEARER = "Bearer ";

    private final JwtProperties jwtProperties;

//    public String generateAccessToken(
//            String jti,
//            String subject,
//            Date issuedAt,
//            Date expiration,
//            Map<String, Object> claims
//    ) {
//        return Jwts.builder()
//                .id(jti)
//                .subject(subject)
//                .issuedAt(issuedAt)
//                .expiration(expiration)
//                .claims(claims)
//                .signWith(secretKey())
//                .compact();
//    }

    public String generateAccessToken(
            String jti,
            AuthenticatedUser user
    ) {
        String subject = user.getUsername();
        Date issuedAt = new Date();
        Date expiration = expiration(jwtProperties.accessExpirationMinutes());
        Map<String, String> claims = user.getClaims();
        SecretKey secretKey = secretKey();
        return Jwts.builder()
                .id(jti)
                .subject(subject)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(claims)
                .signWith(secretKey)
                .compact();
    }

    public String generateAccessToken(
            String jti,
            UserJpaEntity user
    ) {
        String subject = user.getUsername();
        Date issuedAt = new Date();
        Date expiration = expiration(jwtProperties.accessExpirationMinutes());
        Map<String, String> claims = toClaims(user);
        SecretKey secretKey = secretKey();
        return Jwts.builder()
                .id(jti)
                .subject(subject)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(claims)
                .signWith(secretKey)
                .compact();
    }

    private Map<String, String> toClaims(UserJpaEntity user) {
        return Map.of(
                "role", user.getRole().toString(),
                "userId", String.valueOf(user.getId())
        );
    }

//    public String generateRefreshToken(
//            String jti,
//            String subject,
//            Date issuedAt,
//            Date expiration
//    ) {
//        return Jwts.builder()
//                .id(jti)
//                .subject(subject)
//                .issuedAt(issuedAt)
//                .expiration(expiration)
//                .signWith(secretKey())
//                .compact();
//    }

    public String generateRefreshToken(
            String jti,
            AuthenticatedUser user
    ) {
        String subject = user.getUsername();
        Date now = new Date();
        Date expiration = expiration(jwtProperties.refreshExpirationMinutes());
        SecretKey secretKey = secretKey();
        return Jwts.builder()
                .id(jti)
                .subject(subject)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    public String generateRefreshToken(
            String jti,
            UserJpaEntity user
    ) {
        String subject = user.getUsername();
        Date now = new Date();
        Date expiration = expiration(jwtProperties.refreshExpirationMinutes());
        SecretKey secretKey = secretKey();
        return Jwts.builder()
                .id(jti)
                .subject(subject)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();

    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey secretKey() {
        String secret = jwtProperties.secret();
        byte[] base64EncodedSecret = Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(base64EncodedSecret);
    }

    private Date expiration(Integer minutes) {
        Calendar time = Calendar.getInstance();
        time.add(Calendar.MINUTE, minutes);
        return time.getTime();
    }

}
