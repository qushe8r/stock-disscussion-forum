package log.qushe8r.stockdiscussionforum.common.config;

import log.qushe8r.stockdiscussionforum.common.property.KafkaProperties;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.*;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProperties properties;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.bootstrapServers());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<String, StockPriceTicker> stockPriceTickerProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, StockPriceTicker> stockPriceTickerKafkaTemplate() {
        return new KafkaTemplate<>(stockPriceTickerProducerFactory());
    }

    @Bean
    public ProducerFactory<String, SimpleMovingAverage> movingAverageLineProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, SimpleMovingAverage> movingAverageLineKafkaTemplate() {
        return new KafkaTemplate<>(movingAverageLineProducerFactory());
    }

    @Bean
    public ProducerFactory<String, ExponentialMovingAverage> exponentialMovingAverageLineProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, ExponentialMovingAverage> exponentialMovingAverageKafkaTemplate() {
        return new KafkaTemplate<>(exponentialMovingAverageLineProducerFactory());
    }

    @Bean
    public ProducerFactory<String, MACD> mACDProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, MACD> mACDKafkaTemplate() {
        return new KafkaTemplate<>(mACDProducerFactory());
    }

    @Bean
    public ProducerFactory<String, BollingerBand> bollingerBandProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, BollingerBand> bollingerBandKafkaTemplate() {
        return new KafkaTemplate<>(bollingerBandProducerFactory());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.bootstrapServers());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "500");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, "300");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "stock-service-group");

        return props;
    }

    @Bean
    public ConsumerFactory<String, StockPriceTicker> stockPriceTickerConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(StockPriceTicker.class, false));
    }

    @Bean(name = "stockPriceTickerKafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, StockPriceTicker>> stockPriceTickerKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, StockPriceTicker> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stockPriceTickerConsumerFactory());
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, SimpleMovingAverage> simpleMovingAverageConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(SimpleMovingAverage.class, false));
    }

    @Bean(name = "simpleMovingAverageKafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, SimpleMovingAverage>> simpleMovingAverageKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SimpleMovingAverage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(simpleMovingAverageConsumerFactory());
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ExponentialMovingAverage> exponentialMovingAverageConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(ExponentialMovingAverage.class, false));
    }

    @Bean(name = "movingAverageLineKafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ExponentialMovingAverage>> exponentialMovingAverageKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ExponentialMovingAverage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(exponentialMovingAverageConsumerFactory());
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, MACD> mACDConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(MACD.class, false));
    }

    @Bean(name = "mACDKafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MACD>> mACDKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MACD> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(mACDConsumerFactory());
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, BollingerBand> bollingerBandConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(BollingerBand.class, false));
    }

    @Bean(name = "bollingerBandKafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, BollingerBand>> bollingerBandKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BollingerBand> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bollingerBandConsumerFactory());
        factory.setBatchListener(true);
        return factory;
    }

}
