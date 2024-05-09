package log.qushe8r.stockdiscussionforum.security.redis;

import log.qushe8r.stockdiscussionforum.security.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private static final String BLACKLIST_KEY = "blacklist";

    private final TokenRepository tokenRepository;
    private final JwtProperties jwtProperties;
    private final StringRedisTemplate stringRedisTemplate;

    public void save(String jti, Long userId) {
        Long ttl = jwtProperties.refreshExpirationMinutes().longValue() * 60;
        Token token = new Token(jti, userId, ttl);
        tokenRepository.save(token);
    }

    public Optional<Token> findById(String jti) {
        return tokenRepository.findById(jti);
    }

    public List<Token> findByUserId(Long userId) {
        return tokenRepository.findByUserId(userId);
    }

    public void deleteById(String jti) {
        tokenRepository.deleteById(jti);
    }

    public void addBlacklist(List<Token> tokens) {
        String[] jtis = tokens.stream().map(Token::getId)
                .toList()
                .toArray(new String[0]);
        stringRedisTemplate.opsForSet().add(BLACKLIST_KEY, jtis);
    }


    public Boolean isBlacklisted(String jti) {
        return stringRedisTemplate.opsForSet().isMember(BLACKLIST_KEY, jti);
    }

}
