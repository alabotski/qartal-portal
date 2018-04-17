package com.nomis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomis.dto.NodeDto;
import com.nomis.shared.model.ServerStatusInfo;
import com.nomis.shared.response.ServerInfoResponse;
import com.nomis.shared.response.ServerLogOptionResponse;
import com.nomis.shared.response.ServerStatusResponse;
import com.nomis.util.ResourcesUtil;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ServerServiceImpl.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@Service
@Slf4j
public class ServerServiceImpl implements ServerService {

  @Autowired
  private ObjectMapper objectMapper;
  @Inject
  private NodesService nodesService;
  @Inject
  private PropertyService propertyService;

  @Override
  public ServerStatusResponse getServerStatus() throws IOException {
    Map<Long, NodeDto> nodes = nodesService.getAllNodes();
    ServerStatusResponse response = new ServerStatusResponse();
    List<ServerStatusInfo> infoList = nodes.values()
        .stream()
        .map(this::getInfoFromNode)
        .collect(Collectors.toList());
    response.setServerStatusList(infoList);
    response.setWebSocketUrl(propertyService.getStatusSocketUrl());
    return response;
  }

  private ServerStatusInfo getInfoFromNode(NodeDto node) {
    ServerStatusInfo info = new ServerStatusInfo();
    info.setId(node.getId().intValue());
    info.setName(node.getNodeType());
    info.setServerStatus(node.getStatus());
    return info;
  }

  @Override
  public ServerInfoResponse getServerInfo() throws IOException {
    return objectMapper.readValue(ResourcesUtil.getInstance()
        .getResource("ServerInfo.json"), ServerInfoResponse.class);
  }

  @Override
  public ServerLogOptionResponse getServerLogOption() throws IOException {
    return objectMapper.readValue(ResourcesUtil.getInstance()
        .getResource("ServerLogOption.json"), ServerLogOptionResponse.class);
  }
}
