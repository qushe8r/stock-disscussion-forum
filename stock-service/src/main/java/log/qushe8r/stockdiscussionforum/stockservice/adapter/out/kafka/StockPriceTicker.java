package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka;

import java.math.BigDecimal;

public record StockPriceTicker(String itemCode, BigDecimal currentPrice) {
}
