package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import java.util.List;
import java.util.Map;

public interface BollingerBandRedisPort {

    List<String> multiGetMovingAverage(List<String> movingAverageRedisKeys);

    void multiSet(Map<String, String> bollingerBandRedisKeyValues);

}
