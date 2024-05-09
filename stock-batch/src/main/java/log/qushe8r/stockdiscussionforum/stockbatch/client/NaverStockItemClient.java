package log.qushe8r.stockdiscussionforum.stockbatch.client;

import log.qushe8r.stockdiscussionforum.stockbatch.client.dto.Page;
import log.qushe8r.stockdiscussionforum.stockbatch.client.dto.StockItemRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver-stock-item", url = "https://m.stock.naver.com/api/stocks/marketValue")
public interface NaverStockItemClient {

    @GetMapping(path = "/{categoryType}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Page<StockItemRecord> getStockItems(@PathVariable(name = "categoryType") String categoryType,
                                        @RequestParam(name = "page") int page,
                                        @RequestParam(name = "pageSize") int pageSize);

}
