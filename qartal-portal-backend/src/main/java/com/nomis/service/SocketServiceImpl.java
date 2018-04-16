package com.nomis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomis.shared.model.LogLevel;
import com.nomis.shared.model.ServerStatus;
import com.nomis.shared.model.ServerStatusInfo;
import com.nomis.shared.response.LogInfoResponse;
import com.nomis.shared.response.ServerStatusResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * SocketServiceImpl.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@Service
@Slf4j
public class SocketServiceImpl implements SocketService {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private List<WebSocketSession> webSocketSessionList = new ArrayList<>();
  private Map<WebSocketSession, LogLevel> webSocketSessionMap = new HashMap<>();

  @Autowired
  private ServerService serverService;

  @Autowired
  private ObjectMapper objectMapper;

  @Scheduled(fixedRate = 3000)
  public void updateServerStatus() throws IOException {
    ServerStatusResponse serverStatusResponse = generateServerStatusResponse(serverService.getServerStatus()
        .getServerStatusList());
    webSocketSessionList.forEach(webSocketSession -> {
      try {
        webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(serverStatusResponse)));
      } catch (IOException e) {
        log.error("updateServerStatus", e);
      }
    });
  }

  @Scheduled(fixedRate = 3000)
  public void updateLogInfo() {
    webSocketSessionMap.forEach((webSocketSession, logLevel) -> {
      LogInfoResponse logInfoResponse = new LogInfoResponse();
      logInfoResponse.setCurrentTime(formatter.format(LocalDateTime.now()));
      logInfoResponse.setLogLevel(logLevel);
      logInfoResponse.setMessage("Message from server");
      logInfoResponse.setSessionId(webSocketSession.getId());
      try {
        webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(logInfoResponse)));
      } catch (IOException e) {
        log.error("updateLogInfo", e);
      }
    });
  }

  @Override
  public List<WebSocketSession> getWebSocketSessionList() {
    return webSocketSessionList;
  }

  @Override
  public Map<WebSocketSession, LogLevel> getWebSocketSessionMap() {
    return webSocketSessionMap;
  }

  private ServerStatusResponse generateServerStatusResponse(List<ServerStatusInfo> serverStatusInfoList) {
    ServerStatusResponse serverStatusResponse = new ServerStatusResponse();
    List<ServerStatusInfo> serverStatusInfoListGen = new ArrayList<>();

    serverStatusInfoList.forEach(serverInfo -> {
      ServerStatus serverStatus = ServerStatus.getRandomStatus();
      ServerStatusInfo serverStatusInfoGen = new ServerStatusInfo();
      serverStatusInfoGen.setId(serverInfo.getId());
      serverStatusInfoGen.setServerType(serverInfo.getServerType());
      serverStatusInfoGen.setServerStatus(serverStatus);
      serverStatusInfoListGen.add(serverStatusInfoGen);
    });

    serverStatusResponse.setServerStatusList(serverStatusInfoListGen);
    return serverStatusResponse;
  }
}
