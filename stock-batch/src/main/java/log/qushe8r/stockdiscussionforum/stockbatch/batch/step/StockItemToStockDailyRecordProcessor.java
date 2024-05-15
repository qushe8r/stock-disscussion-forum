package log.qushe8r.stockdiscussionforum.stockbatch.batch.step;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import log.qushe8r.stockdiscussionforum.stockbatch.client.NaverStockDailyRecordClient;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockItemJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockItemToStockDailyRecordProcessor implements ItemProcessor<StockItemJpaEntity, List<StockDailyPriceRecordJpaEntity>> {

    private final NaverStockDailyRecordClient client;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public List<StockDailyPriceRecordJpaEntity> process(StockItemJpaEntity item) {
        String symbol = item.getItemCode();
        String body = client.getStockDailyRecords(symbol, "day", 0, 1200);
        String jsonResponse = body.replace("\n", "").replace("\t", "");
        return parseJson1(jsonResponse, item);
    }

    private List<StockDailyPriceRecordJpaEntity> parseJson1(String jsonResponse, StockItemJpaEntity item) {
        List<StockDailyPriceRecordJpaEntity> stockDailyRecordJpaEntities = new ArrayList<>();

        try {
            Gson gson = new Gson();
            String removedTableHeader = "[" + jsonResponse.substring(50);
            Type type = new TypeToken<List<List<Object>>>() {}.getType();
            List<List<Object>> dataList = gson.fromJson(removedTableHeader, type);

            for (int i = 1; i < dataList.size(); i++) {
                StockDailyPriceRecordJpaEntity stockDailyPriceRecordJpaEntity = getStockDailyRecord(item, dataList, i);
                stockDailyRecordJpaEntities.add(stockDailyPriceRecordJpaEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stockDailyRecordJpaEntities;
    }

    private StockDailyPriceRecordJpaEntity getStockDailyRecord(StockItemJpaEntity item, List<List<Object>> dataList, int i) {
        List<Object> rowData = dataList.get(i);

        String date = (String) rowData.get(0);
        String id = date + "-" + item.getItemCode();
        BigDecimal openingPrice = BigDecimal.valueOf((double) rowData.get(1));
        BigDecimal highPrice = BigDecimal.valueOf((double) rowData.get(2));
        BigDecimal lowPrice = BigDecimal.valueOf((double) rowData.get(3));
        BigDecimal closingPrice = BigDecimal.valueOf((double) rowData.get(4));
        BigDecimal volume = BigDecimal.valueOf((double) rowData.get(5));
        double foreignOwnershipPercentage = (double) rowData.get(6);

        return new StockDailyPriceRecordJpaEntity(id, item, LocalDate.parse(date, formatter), openingPrice, highPrice, lowPrice, closingPrice, volume, foreignOwnershipPercentage);
    }

}
