package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.StockItemQueryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@PersistenceAdapter
@RequiredArgsConstructor
public class StockItemQueryPersistenceAdapter implements StockItemQueryPersistencePort {

    private final StockItemJpaRepository repository;

    @Override
    public PageImpl<StockItemJpaEntity> method(String itemName, String itemCode, String categoryType, Pageable pageable) {
        return repository.method(itemName, itemCode, categoryType, pageable);
    }

}
