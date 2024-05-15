package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka;

import java.math.BigDecimal;

public record SimpleMovingAverage(String itemCode, BigDecimal currentPrice, BigDecimal average) {
}
