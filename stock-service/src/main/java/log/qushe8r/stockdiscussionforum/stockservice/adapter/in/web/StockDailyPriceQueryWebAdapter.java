package log.qushe8r.stockdiscussionforum.stockservice.adapter.in.web;

import java.util.List;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockDailyPriceQueryUseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockDailyPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
public class StockDailyPriceQueryWebAdapter {

    private final StockDailyPriceQueryUseCase useCase;

    @GetMapping("/{itemCode}")
    public ResponseEntity<List<StockDailyPriceResponse>> stockDailyPriceQuery(@PathVariable String itemCode,
                                                                              Pageable pageable) {
        List<StockDailyPriceResponse> responses = useCase.stockDailyPrice(itemCode, pageable);
        return ResponseEntity.ok(responses);
    }

}
