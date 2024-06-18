package log.qushe8r.stockdiscussionforum.stockservice.adapter.in.web;

import jakarta.annotation.PostConstruct;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockPriceTickerTestProduceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.net.URI;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class WebSocketAdapter {

    private final WebSocketStompClient webSocketStompClient;
    private final WebSocketHandler webSocketHandler;
    private final StockPriceTickerTestProduceUseCase useCase;

    @PostConstruct
    public void connect() {
        useCase.setExponentialMovingAverageToRedis();
        useCase.setSimpleMovingAverageToRedis();
        log.info("Connecting to WebSocket Server");
        WebSocketClient webSocketClient = webSocketStompClient.getWebSocketClient();
        webSocketClient.execute(webSocketHandler, new WebSocketHttpHeaders(), URI.create("ws://stock-ticker-service:8080/ws"));
    }
}
