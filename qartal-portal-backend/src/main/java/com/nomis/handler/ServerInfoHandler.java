package com.nomis.handler;

import com.google.gson.Gson;
import com.nomis.service.SocketService;
import com.nomis.shared.request.ServerInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * ServerInfoHandler.
 *
 * @author Aliaksei Labotski.
 * @since 4/17/18.
 */
@Component
@Slf4j
public class ServerInfoHandler extends TextWebSocketHandler {

  @Autowired
  private SocketService socketService;

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    log.info("Message received: " + message.getPayload());
    ServerInfoRequest serverInfoRequest = new Gson().fromJson(message.getPayload(), ServerInfoRequest.class);
    socketService.getWebSocketSessionInfoMap()
        .put(session, serverInfoRequest.getId());
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.info("Connected ... " + session.getId());
    socketService.getWebSocketSessionInfoMap()
        .put(session, -1);
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
    log.error("Error occured at sender " + session, throwable);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    log.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    socketService.getWebSocketSessionInfoMap()
        .remove(session);
  }
}
