package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka;

import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.BollingerBandKafkaProducerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@KafkaProducerAdapter
@Component
@RequiredArgsConstructor
public class BollingerBandKafkaProducerAdapter implements BollingerBandKafkaProducerPort {

    private final KafkaTemplate<String, BollingerBand> kafkaTemplate;

    @Override
    public void produce(Map<String, List<BollingerBand>> bollingerBandKafkaTopicPayloads) {
        bollingerBandKafkaTopicPayloads.forEach((topic, payloads) ->
                payloads.forEach(payload -> kafkaTemplate.send(topic, payload)));
    }

}
