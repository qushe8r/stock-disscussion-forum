package log.qushe8r.stockdiscussionforum.stockservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockPriceTickerTestProduceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
@Slf4j
public class KafkaTestWebAdapter {

    private final StockPriceTickerTestProduceUseCase useCase;

    @PostMapping("/test/stock-price-ticker")
    public ResponseEntity<Void> test(@RequestParam(name = "message") String message) {
        log.info("**** message: {}", message);
        useCase.setSimpleMovingAverageToRedis();
        useCase.setExponentialMovingAverageToRedis();
        useCase.produce();
        return ResponseEntity.ok().build();
    }

}
