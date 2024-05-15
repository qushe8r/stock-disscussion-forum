package log.qushe8r.stockdiscussionforum.stockservice.application.port.in;

import java.math.BigDecimal;

public record StockItemResponse(String itemCode, String stockName, String categoryType, BigDecimal openingPrice,
                                BigDecimal closingPrice, BigDecimal volume, BigDecimal fluctuationRate) {
}
