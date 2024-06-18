package log.qushe8r.stockdiscussionforum.common;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class CustomWebSocketHandler extends TextWebSocketHandler {

	private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.sessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		send(message.getPayload());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		this.sessions.remove(session);
	}

	public void send(String message) throws IOException {
		log.info("send message: {}", message);
		synchronized (this.sessions) {
			for (WebSocketSession webSocketSession : sessions) {
				if (webSocketSession.isOpen()) {
					webSocketSession.sendMessage(new TextMessage(message));
				} else {
					this.sessions.remove(webSocketSession);
				}
			}
		}
	}
}
