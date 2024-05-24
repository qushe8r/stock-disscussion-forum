package log.qushe8r.stockdiscussionforum.stockbatch.batch.step;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ItemRedisBulkWriter implements ItemWriter<Map<String, String>> {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void write(Chunk<? extends Map<String, String>> chunk) {
        chunk.getItems().forEach(keyValues -> redisTemplate.opsForValue().multiSet(keyValues));
    }

}
