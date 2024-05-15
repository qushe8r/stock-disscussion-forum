package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface StockDailyPriceRecordJpaRepository extends JpaRepository<StockDailyPriceRecordJpaEntity, String>, StockDailyPriceRecordQueryDslRepository {

    List<StockDailyPriceRecordJpaEntity> query(String itemCode, Pageable pageable);

    @Query("""
            select sp from StockDailyPriceRecordJpaEntity sp join fetch sp.stockItemJpaEntity where sp.date=:yesterday
            """)
    List<StockDailyPriceRecordJpaEntity> findAllQuery(LocalDate yesterday);

}
