package log.qushe8r.stockdiscussionforum.stocktickerservice.application.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import log.qushe8r.stockdiscussionforum.stocktickerservice.adapter.out.persistence.StockItemJpaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import log.qushe8r.stockdiscussionforum.common.CustomWebSocketHandler;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.stocktickerservice.application.port.out.StockItemQueryPersistencePort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class StockTickDataSendService {

    private final CustomWebSocketHandler customWebSocketHandler;
    private final StockItemQueryPersistencePort persistencePort;
    private final ObjectMapper objectMapper;
    private List<StockItemCurrentPrice> stockItemCurrentPrices;

    @Scheduled(fixedRate = 1000)
    public void sendTickData() {
        if (stockItemCurrentPrices == null || stockItemCurrentPrices.isEmpty()) {
            log.info("stockItemCurrentPrices is null or empty");
            stockItemCurrentPrices = new ArrayList<>();
            List<StockItemJpaEntity> stockItems = persistencePort.findAllStockItemsWhenLastBisinessDay();
            for (StockItemJpaEntity stockItem : stockItems) {
                String itemCode = stockItem.getItemCode();
                BigDecimal latestPrice = stockItem.getStockDailyRecordJpaEntities()
                        .getLast()
                        .getClosingPrice();
                stockItemCurrentPrices.add(new StockItemCurrentPrice(itemCode, latestPrice, LocalDateTime.now()));
            }
        }
        stockItemCurrentPrices.forEach(stockItemCurrentPrice -> {
            try {
                String message = objectMapper.writeValueAsString(stockItemCurrentPrice);
                customWebSocketHandler.send(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
