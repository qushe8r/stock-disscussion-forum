package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.ExponentialMovingAverage;

import java.util.List;
import java.util.Map;

public interface ExponentialMovingAverageKafkaProducerPort {

    void produce(Map<String, List<ExponentialMovingAverage>> exponentialMovingAverageKafkaTopicPayload);

}
