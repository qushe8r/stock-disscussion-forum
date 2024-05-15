package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockItemJpaEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface StockItemQueryPersistencePort {

    PageImpl<StockItemJpaEntity> method(String itemName, String itemCode, String categoryType, Pageable pageable);

}
