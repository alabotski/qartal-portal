package com.nomis.service;

import com.nomis.handler.SocketHandler;
import com.nomis.shared.model.ServerStatus;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

/**
 * SocketServiceImpl.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@Service
@Slf4j
public class SocketServiceImpl implements SocketService {

  @Scheduled(fixedRate = 3000)
  public void updateServerStatus() {
    SocketHandler.getWebSocketSession()
        .forEach(
            sessionList -> {
              ServerStatus serverStatus = ServerStatus.getRandomStatus();
              sessionList.forEach(session -> {
                try {
                  session.sendMessage(new TextMessage(serverStatus.name()));
                } catch (IOException e) {
                  log.error("updateServerStatus", e);
                }
              });
            }
        );
  }

}
