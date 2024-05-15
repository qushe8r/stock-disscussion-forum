package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.redis;

import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.BollingerBandRedisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@RedisAdapter
@Component
@RequiredArgsConstructor
public class BollingerBandRedisAdapter implements BollingerBandRedisPort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public List<String> multiGetMovingAverage(List<String> movingAverageRedisKeys) {
        return redisTemplate.opsForValue().multiGet(movingAverageRedisKeys);
    }

    @Override
    public void multiSet(Map<String, String> bollingerBandRedisKeyValues) {
        redisTemplate.opsForValue().multiSet(bollingerBandRedisKeyValues);
    }

}
