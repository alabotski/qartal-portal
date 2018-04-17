package com.nomis.service;

import static com.nomis.shared.model.ServerStatus.NOT_ACTUAL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomis.dto.NodeDto;
import com.nomis.shared.model.LogLevel;
import com.nomis.shared.model.ServerInfo;
import com.nomis.shared.model.ServerStatus;
import com.nomis.shared.model.ServerStatusInfo;
import com.nomis.shared.response.LogInfoResponse;
import com.nomis.shared.response.ServerInfoResponse;
import com.nomis.shared.response.ServerStatusResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
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
  private Map<WebSocketSession, LogLevel> webSocketSessionLogMap = new HashMap<>();
  private Map<WebSocketSession, Integer> webSocketSessionInfoMap = new HashMap<>();

  @Autowired
  private ServerService serverService;

  @Inject
  private NodesService nodesService;

  @Autowired
  private ObjectMapper objectMapper;

  @Inject
  private NodesServiceImpl nodesService;

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
    webSocketSessionLogMap.forEach((webSocketSession, logLevel) -> {
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

  @Scheduled(fixedRate = 3000)
  public void updateServiceInfo() {
    webSocketSessionInfoMap.forEach((webSocketSession, id) -> {
      ServerInfoResponse serverInfoResponse = new ServerInfoResponse();
      List<ServerInfo> serverInfoList = new ArrayList<>();
      NodeDto nodeDto = nodesService.getNodeById(id.longValue());
      nodeDto.getNodeInfo().entrySet().stream()
          .filter(Objects::nonNull)
          .forEach(entry -> {
            ServerInfo info = new ServerInfo();
            info.setKey(entry.getKey());
            info.setValue(entry.getValue().toString());
            serverInfoList.add(info);
          });
      serverInfoResponse.setServerInfoList(serverInfoList);
      try {
        webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(serverInfoResponse)));
      } catch (IOException e) {
        log.error("updateServiceInfo", e);
      }
    });
  }

  @Override
  public List<WebSocketSession> getWebSocketSessionList() {
    return webSocketSessionList;
  }

  @Override
  public Map<WebSocketSession, LogLevel> getWebSocketSessionLogMap() {
    return webSocketSessionLogMap;
  }

  @Override
  public Map<WebSocketSession, Integer> getWebSocketSessionInfoMap() {
    return webSocketSessionInfoMap;
  }

  private ServerStatusResponse generateServerStatusResponse(List<ServerStatusInfo> serverStatusInfoList) {
    ServerStatusResponse serverStatusResponse = new ServerStatusResponse();
    List<ServerStatusInfo> serverStatusInfoListGen = new ArrayList<>();

    serverStatusInfoList.forEach(serverInfo -> {
      ServerStatus serverStatus = NOT_ACTUAL;
      Map<Long, NodeDto> nodes = nodesService.getAllNodes();
      switch (serverInfo.getName()) {
        case "NPO":
          serverStatus = nodes.values()
              .stream()
              .filter(node -> node.getNodeType()
                  .equalsIgnoreCase("NPO"))
              .map(node -> node.getStatus())
              .findFirst()
              .orElse(ServerStatus.NOT_ACTUAL);
          break;
        case "JM":
          serverStatus = nodes.values()
              .stream()
              .filter(node -> node.getNodeType()
                  .equalsIgnoreCase("JM"))
              .map(node -> node.getStatus())
              .findFirst()
              .orElse(ServerStatus.NOT_ACTUAL);
          break;
        case "RabbitMQ":
          serverStatus = nodes.values()
              .stream()
              .filter(node -> node.getNodeType()
                  .equalsIgnoreCase("RabbitMQ"))
              .map(node -> node.getStatus())
              .findFirst()
              .orElse(ServerStatus.NOT_ACTUAL);
          break;
        default:
          serverStatus = NOT_ACTUAL;
      }

      //      ServerStatus serverStatus = ServerStatus.getRandomStatus();
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
