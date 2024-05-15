package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface StockItemQueryDslRepository {

    PageImpl<StockItemJpaEntity> method(String itemName, String itemCode, String categoryType, Pageable pageable);

}
