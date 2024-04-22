package log.qushe8r.stockdiscussionforum.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
@RequiredArgsConstructor
public class VerificationCodeService {
    private static final String VERIFICATION_CODE_PREFIX = "verification_code:";

    private final StringRedisTemplate redisTemplate;

    public void saveVerificationCode(Long userId, String code) {
        redisTemplate.opsForValue().set(VERIFICATION_CODE_PREFIX + userId, code, Duration.ofMinutes(10));
    }

    public String getVerificationCode(Long userId) {
        return redisTemplate.opsForValue().get(VERIFICATION_CODE_PREFIX + userId);
    }

    public void removeVerificationCode(Long userId) {
        redisTemplate.delete(VERIFICATION_CODE_PREFIX + userId);
    }

}
