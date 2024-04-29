package log.qushe8r.stockdiscussionforum.apigateway.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import log.qushe8r.stockdiscussionforum.apigateway.jwt.TokenProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtVerificationFilter extends AbstractGatewayFilterFactory<JwtVerificationFilter.Config> {

    private final TokenProcessor tokenProcessor;

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            try {
                String accessToken = getAccessToken(request); // req
                Claims claims = tokenProcessor.extractClaims(accessToken);
                attachAuthorizedUserInfoToHeader(request, claims);
            } catch (JwtException e) {
                return onError(response, HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        });
    }

    private String getAccessToken(ServerHttpRequest request) {
        String authorizationHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && !authorizationHeader.startsWith(TokenProcessor.BEARER)) {
            throw new IllegalArgumentException("Authorization header is invalid");
        }
        return authorizationHeader.substring(TokenProcessor.BEARER.length());
    }

    private void attachAuthorizedUserInfoToHeader(ServerHttpRequest request, Claims claims) {
        request.mutate()
                .header("username", claims.getSubject())
                .header("userId", String.valueOf(claims.get("userId")))
                .build();
    }

    private Mono<Void> onError(ServerHttpResponse response, HttpStatus httpStatus) {
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    public static class Config {
    }

}
