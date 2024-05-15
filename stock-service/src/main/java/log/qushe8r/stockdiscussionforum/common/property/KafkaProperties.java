package log.qushe8r.stockdiscussionforum.common.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record KafkaProperties(@Value("#{'${kafka.bootstrapServers}'.split(',')}") List<String> bootstrapServers) {
}
