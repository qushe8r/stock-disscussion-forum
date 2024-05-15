package log.qushe8r.stockdiscussionforum.stockservice.application.port.in;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.SimpleMovingAverage;

import java.util.List;

public interface BollingerBandUseCase {

    void operate(List<SimpleMovingAverage> simpleMovingAverages);

}
