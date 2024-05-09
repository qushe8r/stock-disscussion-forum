package log.qushe8r.stockdiscussionforum.stockbatch.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver-stock-daily-record", url = "https://api.finance.naver.com/siseJson.naver")
public interface NaverStockDailyRecordClient {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getStockDailyRecords(@RequestParam(name = "symbol") String symbol,
                                @RequestParam(name = "timeframe") String timeframe,
                                @RequestParam(name = "requestType") int requestType,
                                @RequestParam(name = "count") int count);

}
