package com.nomis.handler;

import com.google.gson.Gson;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * SocketHandler9.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@Component
@Slf4j
public class SocketHandler9 extends TextWebSocketHandler {

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    log.info("message received: " + message.getPayload());
    for (WebSocketSession webSocketSession : SocketHandler.getWebSocketSession(SocketHandler.SERVER9)) {
      Map value = new Gson().fromJson(message.getPayload(), Map.class);
      webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
    }
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.info("Connected ... " + session.getId());
    //the messages will be broadcasted to all users.
    SocketHandler.getWebSocketSession(SocketHandler.SERVER9)
        .add(session);
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
    log.error("error occured at sender " + session, throwable);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    log.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    SocketHandler.getWebSocketSession(SocketHandler.SERVER9)
        .remove(session);
  }
}
