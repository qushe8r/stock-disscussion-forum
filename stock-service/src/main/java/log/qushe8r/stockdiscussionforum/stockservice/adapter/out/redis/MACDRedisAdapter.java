package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.redis;

import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.MACDRedisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@RedisAdapter
@Component
@RequiredArgsConstructor
public class MACDRedisAdapter implements MACDRedisPort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public List<String> multiGetExponentialMovingAverages(List<String> exponentialMovingAverageRedisKeys) {
        return redisTemplate.opsForValue().multiGet(exponentialMovingAverageRedisKeys);
    }

    @Override
    public void multiSetMACD(Map<String, String> mACDRedisKeyValues) {
        redisTemplate.opsForValue().multiSet(mACDRedisKeyValues);
    }

}
