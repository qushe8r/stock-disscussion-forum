package log.qushe8r.stockdiscussionforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NewsFeedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsFeedServiceApplication.class, args);
    }

}
