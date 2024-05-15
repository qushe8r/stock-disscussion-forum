package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka;

import java.math.BigDecimal;

public record ExponentialMovingAverage(String itemCode, BigDecimal exponentialMovingAverage) {
}
