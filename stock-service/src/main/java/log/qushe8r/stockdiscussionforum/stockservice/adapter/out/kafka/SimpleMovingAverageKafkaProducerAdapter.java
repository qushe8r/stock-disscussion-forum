package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka;

import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.SimpleMovingAverageKafkaProducerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@StockPriceTickerKafkaProducerPortAdapter
@Component
@RequiredArgsConstructor
public class SimpleMovingAverageKafkaProducerAdapter implements SimpleMovingAverageKafkaProducerPort {

    private final KafkaTemplate<String, SimpleMovingAverage> kafkaTemplate;

    @Override
    public void produce(Map<String, List<SimpleMovingAverage>> movingAverageKafkaTopicPayload) {
        movingAverageKafkaTopicPayload.values()
                .forEach(list -> System.out.println("size: " + list.size()));

        movingAverageKafkaTopicPayload.forEach((topic, movingAverageLines) ->
            movingAverageLines.forEach(movingAverageLine -> kafkaTemplate.send(topic, movingAverageLine)));
    }

}
