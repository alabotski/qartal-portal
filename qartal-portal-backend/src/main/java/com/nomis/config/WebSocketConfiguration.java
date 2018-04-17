package com.nomis.config;

import com.nomis.handler.JobManagerHandler;
import com.nomis.handler.LogInfoHandler;
import com.nomis.handler.ServerInfoHandler;
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
  private ServerInfoHandler serverInfoHandler;

  @Autowired
  private LogInfoHandler logInfoHandler;

  @Autowired
  private JobManagerHandler jobManagerHandler;

  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(serverInfoHandler, "/serverStatus")
        .setAllowedOrigins("*");
    registry.addHandler(logInfoHandler, "/logInfo")
        .setAllowedOrigins("*");
    registry.addHandler(jobManagerHandler, "/jobmanager")
        .setAllowedOrigins("*");
  }

  @Bean
  public TaskScheduler taskScheduler() {
    return new ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor());
  }
}
