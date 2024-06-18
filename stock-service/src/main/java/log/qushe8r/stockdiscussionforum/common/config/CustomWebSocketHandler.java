package log.qushe8r.stockdiscussionforum.common.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockTickData;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockTickDataUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private final StockTickDataUseCase useCase;
    private final ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("websocket connection established");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {
        StockTickData stockTickData = objectMapper.readValue(message.getPayload(), StockTickData.class);
        useCase.produce(stockTickData);
    }

}
