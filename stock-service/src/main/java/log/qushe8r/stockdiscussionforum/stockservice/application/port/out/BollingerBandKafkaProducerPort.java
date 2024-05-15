package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.BollingerBand;

import java.util.List;
import java.util.Map;

public interface BollingerBandKafkaProducerPort {

    void produce(Map<String, List<BollingerBand>> bollingerBandKafkaTopicPayloads);

}
