package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.MACD;

import java.util.List;
import java.util.Map;

public interface MACDKafkaProducerPort {

    void produce(Map<String, List<MACD>> mACDKafkaTopicPayloads);

}
