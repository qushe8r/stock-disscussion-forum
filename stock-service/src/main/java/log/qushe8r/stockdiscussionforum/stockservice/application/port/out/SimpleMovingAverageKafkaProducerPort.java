package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.SimpleMovingAverage;

import java.util.List;
import java.util.Map;

public interface SimpleMovingAverageKafkaProducerPort {

    void produce(Map<String, List<SimpleMovingAverage>> movingAverageKafkaTopicPayload);

}
