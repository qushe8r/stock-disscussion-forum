package log.qushe8r.stockdiscussionforum.stockservice.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockItemQueryUseCase {

    Page<StockItemResponse> method(String itemName, String itemCode, String categoryType, Pageable pageable);

}
