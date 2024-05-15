package log.qushe8r.stockdiscussionforum.stockservice.adapter.in.kafka;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.SimpleMovingAverage;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.BollingerBandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

//@KafkaConsumerAdapter
@Component
@RequiredArgsConstructor
public class BollingerBandKafkaConsumerAdapter {

    private final BollingerBandUseCase useCase;

    @KafkaListener(
            topics =  "simple-moving-average-20",
            containerFactory = "simpleMovingAverageKafkaListenerContainerFactory"
    )
    public void listen(List<SimpleMovingAverage> simpleMovingAverages) {
        useCase.operate(simpleMovingAverages);
    }

}
