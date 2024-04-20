package log.qushe8r.stockdiscussionforum.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VerificationCodeService {
    private static final String VERIFICATION_CODE_PREFIX = "verification_code:";

    private final StringRedisTemplate redisTemplate;

    public void saveVerificationCode(Long userId, String code) {
        redisTemplate.opsForValue().set(VERIFICATION_CODE_PREFIX + userId, code);
    }

    public String getVerificationCode(Long userId) {
        return redisTemplate.opsForValue().get(VERIFICATION_CODE_PREFIX + userId);
    }

}
