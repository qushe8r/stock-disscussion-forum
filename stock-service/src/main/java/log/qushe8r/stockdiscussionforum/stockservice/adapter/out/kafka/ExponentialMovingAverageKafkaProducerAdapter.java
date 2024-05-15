package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka;

import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.ExponentialMovingAverageKafkaProducerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@KafkaProducerAdapter
@Component
@RequiredArgsConstructor
public class ExponentialMovingAverageKafkaProducerAdapter implements ExponentialMovingAverageKafkaProducerPort {

    private final KafkaTemplate<String, ExponentialMovingAverage> kafkaTemplate;

    @Override
    public void produce(Map<String, List<ExponentialMovingAverage>> exponentialMovingAverageKafkaTopicPayload) {
        exponentialMovingAverageKafkaTopicPayload.forEach((topic, exponentialMovingAveragePayloads) ->
                exponentialMovingAveragePayloads.forEach(exponentialMovingAveragePayload -> kafkaTemplate.send(topic, exponentialMovingAveragePayload)));
    }

}
