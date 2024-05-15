package log.qushe8r.stockdiscussionforum.stockservice.application.port.in;

import java.math.BigDecimal;

public record StockDailyPriceResponse(String id,
                                      String date,
                                      BigDecimal openingPrice,
                                      BigDecimal highPrice,
                                      BigDecimal lowPrice,
                                      BigDecimal closingPrice,
                                      BigDecimal volume,
                                      double foreignOwnershipPercentage
) {
}