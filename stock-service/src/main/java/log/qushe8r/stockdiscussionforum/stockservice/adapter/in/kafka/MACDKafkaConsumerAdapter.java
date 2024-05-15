package log.qushe8r.stockdiscussionforum.stockservice.adapter.in.kafka;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.SimpleMovingAverage;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.MACDUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

//@KafkaConsumerAdapter
@Component
@RequiredArgsConstructor
public class MACDKafkaConsumerAdapter {

    private final MACDUseCase useCase;
//    private final RedisTemplate<String, String> redisTemplate;
//
//    private static final Integer[] DAYS = {12, 26};
//    private static final String SIMPLE_MOVING_AVERAGE_REDIS_KEY = "simple-moving-average:%d:%s";

    @KafkaListener(
            topics = "simple-moving-average-12",
            containerFactory = "simpleMovingAverageKafkaListenerContainerFactory"
    )
    public void listen(List<SimpleMovingAverage> simpleMovingAverages) {
        System.out.println("MACDKafkaConsumerAdapter1: " + simpleMovingAverages.size());
//
//        List<String> redisKeys = simpleMovingAverages.stream()
//                .map(SimpleMovingAverage::itemCode)
//                .flatMap(itemCode -> Arrays.stream(DAYS)
//                        .map(day -> String.format(SIMPLE_MOVING_AVERAGE_REDIS_KEY, day, itemCode)))
//                .toList();
//
//        List<String> strings = redisTemplate.opsForValue().multiGet(redisKeys);
//
//        System.out.println("MACDKafkaConsumerAdapter2: " + strings.size());
        useCase.operate(simpleMovingAverages);
    }

}
