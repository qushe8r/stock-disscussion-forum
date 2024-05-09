package log.qushe8r.stockdiscussionforum.apigateway.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record TokenProperties(@Value("${jwt.secret}") String secret) {
}
