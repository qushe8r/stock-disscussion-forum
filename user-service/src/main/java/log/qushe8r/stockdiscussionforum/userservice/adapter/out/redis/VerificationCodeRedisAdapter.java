package log.qushe8r.stockdiscussionforum.userservice.adapter.out.redis;

import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserRegistrationVerificationCodeRedisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class VerificationCodeRedisAdapter implements UserRegistrationVerificationCodeRedisPort {

    private static final String VERIFICATION_CODE_PREFIX = "verification_code:";

    private final StringRedisTemplate redisTemplate;

    @Override
    public void saveVerificationCode(Long userId, String code) {
        redisTemplate.opsForValue().set(VERIFICATION_CODE_PREFIX + userId, code, Duration.ofMinutes(10));
    }

    @Override
    public String getVerificationCode(Long userId) {
        return redisTemplate.opsForValue().get(VERIFICATION_CODE_PREFIX + userId);
    }

    @Override
    public void removeVerificationCode(Long userId) {
        redisTemplate.delete(VERIFICATION_CODE_PREFIX + userId);
    }

}
