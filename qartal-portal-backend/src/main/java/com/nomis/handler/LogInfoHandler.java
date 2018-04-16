package com.nomis.handler;

import com.google.gson.Gson;
import com.nomis.service.SocketService;
import com.nomis.shared.model.LogLevel;
import com.nomis.shared.request.LogInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * LogHandler.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
@Component
@Slf4j
public class LogInfoHandler extends TextWebSocketHandler {

  @Autowired
  private SocketService socketService;

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    log.info("Message received: " + message.getPayload());
    LogInfoRequest logInfoRequest = new Gson().fromJson(message.getPayload(), LogInfoRequest.class);
    socketService.getWebSocketSessionMap()
        .put(session, logInfoRequest.getLogLevel());
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.info("Connected ... " + session.getId());
    socketService.getWebSocketSessionMap()
        .put(session, LogLevel.ALL);
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
    log.error("Error occured at sender " + session, throwable);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    log.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    socketService.getWebSocketSessionMap()
        .remove(session);
  }
}
