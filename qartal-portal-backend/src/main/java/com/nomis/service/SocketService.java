package com.nomis.service;

import com.nomis.shared.model.LogLevel;
import java.util.List;
import java.util.Map;
import org.springframework.web.socket.WebSocketSession;

/**
 * SocketService.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public interface SocketService {

  List<WebSocketSession> getWebSocketSessionList();

  Map<WebSocketSession, LogLevel> getWebSocketSessionLogMap();

  Map<WebSocketSession, Integer> getWebSocketSessionInfoMap();


}
