package log.qushe8r.stockdiscussionforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockTickerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockTickerServiceApplication.class, args);
	}

}