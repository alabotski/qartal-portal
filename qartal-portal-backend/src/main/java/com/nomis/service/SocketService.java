package com.nomis.service;

import java.util.List;
import org.springframework.web.socket.WebSocketSession;

/**
 * SocketService.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public interface SocketService {

  List<WebSocketSession> getWebSocketSessionList();
}
