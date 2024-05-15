package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import java.util.List;
import java.util.Map;

public interface SimpleMovingAverageRedisPort {

    List<String> multiGet(List<String> key);

    void multiSet(Map<String, String> keyValue);

}
