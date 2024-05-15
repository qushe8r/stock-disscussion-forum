package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka;

import java.math.BigDecimal;

public record BollingerBand(String itemCode, BigDecimal high, BigDecimal low) {
}
