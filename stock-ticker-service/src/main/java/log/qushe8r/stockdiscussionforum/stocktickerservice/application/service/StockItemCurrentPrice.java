package log.qushe8r.stockdiscussionforum.stocktickerservice.application.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockItemCurrentPrice(String itemCode, BigDecimal currentPrice, LocalDateTime currentTime) {
}
