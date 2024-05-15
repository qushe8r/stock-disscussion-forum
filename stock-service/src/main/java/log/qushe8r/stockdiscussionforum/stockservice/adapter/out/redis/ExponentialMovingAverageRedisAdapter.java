package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.redis;

import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.ExponentialMovingAverageRedisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@RedisPort
@Component
@RequiredArgsConstructor
public class ExponentialMovingAverageRedisAdapter implements ExponentialMovingAverageRedisPort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public List<String> multiGetExponentialMovingAverage(List<String> exponentialMovingAverageRedisKeys) {
        return redisTemplate.opsForValue().multiGet(exponentialMovingAverageRedisKeys);
    }

    @Override
    public void multiSetExponentialAverageRedisKeyValues(Map<String, String> exponentialMovingAverageRedisKeyValues) {
        redisTemplate.opsForValue().multiSet(exponentialMovingAverageRedisKeyValues);
    }

}
