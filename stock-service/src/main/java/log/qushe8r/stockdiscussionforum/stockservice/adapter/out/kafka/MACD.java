package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka;

import java.math.BigDecimal;

public record MACD(String itemCode, BigDecimal mACD) {
}
