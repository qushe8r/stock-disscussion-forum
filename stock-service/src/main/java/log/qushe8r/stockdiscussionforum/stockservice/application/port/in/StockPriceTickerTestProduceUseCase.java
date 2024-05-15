package log.qushe8r.stockdiscussionforum.stockservice.application.port.in;

public interface StockPriceTickerTestProduceUseCase {

    void produce();

    void setSimpleMovingAverageToRedis();

    void setExponentialMovingAverageToRedis();

}
