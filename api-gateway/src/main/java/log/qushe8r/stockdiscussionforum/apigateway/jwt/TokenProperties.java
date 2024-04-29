package log.qushe8r.stockdiscussionforum.apigateway.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record TokenProperties(@Value("${jwt.secret:JWT_SECRET_KEY}") String secret) {
}
