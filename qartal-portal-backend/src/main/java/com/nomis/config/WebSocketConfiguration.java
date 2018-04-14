package com.nomis.config;

import com.nomis.handler.SocketHandler1;
import com.nomis.handler.SocketHandler10;
import com.nomis.handler.SocketHandler2;
import com.nomis.handler.SocketHandler3;
import com.nomis.handler.SocketHandler4;
import com.nomis.handler.SocketHandler5;
import com.nomis.handler.SocketHandler6;
import com.nomis.handler.SocketHandler7;
import com.nomis.handler.SocketHandler8;
import com.nomis.handler.SocketHandler9;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocketConfiguration.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@SpringBootConfiguration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

  @Autowired
  private SocketHandler1 socketHandler1;
  @Autowired
  private SocketHandler2 socketHandler2;
  @Autowired
  private SocketHandler3 socketHandler3;
  @Autowired
  private SocketHandler4 socketHandler4;
  @Autowired
  private SocketHandler5 socketHandler5;
  @Autowired
  private SocketHandler6 socketHandler6;
  @Autowired
  private SocketHandler7 socketHandler7;
  @Autowired
  private SocketHandler8 socketHandler8;
  @Autowired
  private SocketHandler9 socketHandler9;
  @Autowired
  private SocketHandler10 socketHandler10;

  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(socketHandler1, "/server1")
        .setAllowedOrigins("*");
    registry.addHandler(socketHandler2, "/server2")
        .setAllowedOrigins("*");
    registry.addHandler(socketHandler3, "/server3")
        .setAllowedOrigins("*");
    registry.addHandler(socketHandler4, "/server4")
        .setAllowedOrigins("*");
    registry.addHandler(socketHandler5, "/server5")
        .setAllowedOrigins("*");
    registry.addHandler(socketHandler6, "/server6")
        .setAllowedOrigins("*");
    registry.addHandler(socketHandler7, "/server7")
        .setAllowedOrigins("*");
    registry.addHandler(socketHandler8, "/server8")
        .setAllowedOrigins("*");
    registry.addHandler(socketHandler9, "/server9")
        .setAllowedOrigins("*");
    registry.addHandler(socketHandler10, "/server10")
        .setAllowedOrigins("*");
  }

  @Bean
  public TaskScheduler taskScheduler() {
    return new ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor());
  }
}
