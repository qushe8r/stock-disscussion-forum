package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemJpaRepository extends JpaRepository<StockItemJpaEntity, String>, StockItemQueryDslRepository {

    PageImpl<StockItemJpaEntity> method(String itemName, String itemCode, String categoryType, Pageable pageable);

}
