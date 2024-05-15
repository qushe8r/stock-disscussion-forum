package log.qushe8r.stockdiscussionforum.stockservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockItemJpaEntity;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockItemQueryUseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockItemResponse;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.StockItemQueryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class StockItemQueryService implements StockItemQueryUseCase {

    private final StockItemQueryPersistencePort persistencePort;
    private final StockItemMapper mapper;

    @Override
    public Page<StockItemResponse> method(String itemName, String itemCode, String categoryType, Pageable pageable) {
        PageImpl<StockItemJpaEntity> pageStockItems = persistencePort.method(itemName, itemCode, categoryType, pageable);
        Pageable page = pageStockItems.getPageable();
        long totalElements = pageStockItems.getTotalElements();

        List<StockItemJpaEntity> list = pageStockItems.getContent();
        List<StockItemResponse> response = list
                .stream().map(mapper::toResponse)
                .toList();

        return new PageImpl<>(response, page, totalElements);
    }

}
