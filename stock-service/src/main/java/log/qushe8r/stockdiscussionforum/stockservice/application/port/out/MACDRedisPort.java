package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import java.util.List;
import java.util.Map;

public interface MACDRedisPort {

    List<String> multiGetExponentialMovingAverages(List<String> exponentialMovingAverageRedisKeys);

    void multiSetMACD(Map<String, String> mACDRedisKeyValues);

}
