package log.qushe8r.stockdiscussionforum.common.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record ApplicationProperties(@Value("${application.domain}") String domain) {
}
