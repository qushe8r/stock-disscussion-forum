package log.qushe8r.stockdiscussionforum.security.service;


import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import log.qushe8r.stockdiscussionforum.security.jwt.JwtProcessor;
import log.qushe8r.stockdiscussionforum.security.redis.Token;
import log.qushe8r.stockdiscussionforum.security.redis.TokenService;
import log.qushe8r.stockdiscussionforum.security.utils.CookieCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtAuthService {
    private final JwtProcessor jwtProcessor;
    private final CookieCreator cookieCreator;
    //    private final ;
    private final TokenService tokenService;

    public HttpHeaders reissue(String refreshToken) {
        Claims claims = jwtProcessor.extractClaims(refreshToken);
        String jti = claims.getId();
        String username = claims.getSubject();

//        tokenService.findById(jti)
//                .orElseThrow(() -> new JWTException(JwtExceptionCode.TOKEN_NOT_FOUND));
//        User user = userService.findByUsername(username);

//        String generatedAccess = jwtProcessor.generateAccessToken(jti, user);
//        String generatedRefresh = jwtProcessor.generateRefreshToken(jti, user);
//
//        //TODO: ttl 바뀌는지 확인 해야함.
//        tokenService.save(jti, user.getId());
//
//        ResponseCookie cookie = cookieCreator.createResponseCookie(generatedRefresh);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.AUTHORIZATION, JwtProcessor.BEARER + generatedAccess);
//        httpHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString());
//
//        return httpHeaders;
        return null;
    }

    public HttpHeaders logoutAllDevices(Long userId) {
        List<Token> tokens = tokenService.findByUserId(userId);
        tokenService.addBlacklist(tokens);
        Cookie expiredRefresh = cookieCreator.createExpired();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, expiredRefresh.toString());

        return httpHeaders;
    }

}
