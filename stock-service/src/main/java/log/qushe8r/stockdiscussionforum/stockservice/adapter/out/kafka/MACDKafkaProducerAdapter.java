package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka;

import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.MACDKafkaProducerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@KafkaProducerAdater
@Component
@RequiredArgsConstructor
public class MACDKafkaProducerAdapter implements MACDKafkaProducerPort {

    private final KafkaTemplate<String, MACD> kafkaTemplate;

    @Override
    public void produce(Map<String, List<MACD>> mACDKafkaTopicPayloads) {
        mACDKafkaTopicPayloads.forEach((topic, payloads) ->
                payloads.forEach(payload -> kafkaTemplate.send(topic, payload)));
    }

}
