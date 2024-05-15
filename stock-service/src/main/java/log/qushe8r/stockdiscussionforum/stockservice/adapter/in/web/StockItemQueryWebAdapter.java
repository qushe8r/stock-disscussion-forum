package log.qushe8r.stockdiscussionforum.stockservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockItemQueryUseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
public class StockItemQueryWebAdapter {

    private final StockItemQueryUseCase useCase;

    @GetMapping
    public ResponseEntity<Page<StockItemResponse>> method(@RequestParam(name = "itemName", required = false) String itemName,
                                                          @RequestParam(name = "itemCode", required = false) String itemCode,
                                                          @RequestParam(name = "categoryType",required = false) String categoryType,
                                                          Pageable pageable) {
        Page<StockItemResponse> responses = useCase.method(itemName, itemCode, categoryType, pageable);
        return ResponseEntity.ok(responses);
    }

}
