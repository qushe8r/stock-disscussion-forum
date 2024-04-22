package log.qushe8r.stockdiscussionforum.security.service;


import io.jsonwebtoken.Claims;
import log.qushe8r.stockdiscussionforum.security.exception.JwtException;
import log.qushe8r.stockdiscussionforum.security.exception.JwtExceptionCode;
import log.qushe8r.stockdiscussionforum.security.jwt.JwtProcessor;
import log.qushe8r.stockdiscussionforum.security.redis.TokenService;
import log.qushe8r.stockdiscussionforum.security.utils.CookieCreator;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import log.qushe8r.stockdiscussionforum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtAuthService {
    private final JwtProcessor jwtProcessor;
    private final CookieCreator cookieCreator;
    private final UserService userService;
    private final TokenService tokenService;

    public HttpHeaders reissue(String refreshToken) {
        Claims claims = jwtProcessor.extractClaims(refreshToken);
        String jti = claims.getId();
        String username = claims.getSubject();

        tokenService.findById(jti)
                .orElseThrow(() -> new JwtException(JwtExceptionCode.TOKEN_NOT_FOUND));
        User user = userService.findByUsername(username);

        String generatedAccess = jwtProcessor.generateAccessToken(jti, user);
        String generatedRefresh = jwtProcessor.generateRefreshToken(jti, user);

        //TODO: ttl 바뀌는지 확인 해야함.
        tokenService.save(jti, user.getId());

        ResponseCookie cookie = cookieCreator.createResponseCookie(generatedRefresh);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, JwtProcessor.BEARER + generatedAccess);
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString());

        return httpHeaders;
    }

}
