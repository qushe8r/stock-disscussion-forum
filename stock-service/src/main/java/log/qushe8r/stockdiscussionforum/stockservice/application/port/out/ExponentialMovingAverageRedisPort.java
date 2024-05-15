package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import java.util.List;
import java.util.Map;

public interface ExponentialMovingAverageRedisPort {

    List<String> multiGetExponentialMovingAverage(List<String> exponentialMovingAverageRedisKeys);

    void multiSetExponentialAverageRedisKeyValues(Map<String, String> exponentialMovingAverageRedisKeyValues);

}
