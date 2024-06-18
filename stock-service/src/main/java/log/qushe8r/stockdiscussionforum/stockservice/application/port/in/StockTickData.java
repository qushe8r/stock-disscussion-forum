package log.qushe8r.stockdiscussionforum.stockservice.application.port.in;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockTickData(String itemCode, BigDecimal currentPrice, LocalDateTime currentTime) {
}
