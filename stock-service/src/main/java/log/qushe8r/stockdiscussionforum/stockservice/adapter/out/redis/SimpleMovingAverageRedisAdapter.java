package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.redis;

import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.SimpleMovingAverageRedisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@RedisAdapter
@Component
@RequiredArgsConstructor
public class SimpleMovingAverageRedisAdapter implements SimpleMovingAverageRedisPort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public List<String> multiGet(List<String> key) {
        return redisTemplate.opsForValue().multiGet(key);
    }

    @Override
    public void multiSet(Map<String, String> keyValue) {
        redisTemplate.opsForValue().multiSet(keyValue);
    }

}


