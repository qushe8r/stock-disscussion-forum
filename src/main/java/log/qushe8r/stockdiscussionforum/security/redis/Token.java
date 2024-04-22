package log.qushe8r.stockdiscussionforum.security.redis;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@AllArgsConstructor
@RedisHash(value = "token")
public class Token {
    @Id
    private String id;

    @Indexed
    private Long userId;

    @TimeToLive
    private Long ttl;
}
