package com.nomis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomis.shared.model.ServerInfo;
import com.nomis.shared.request.ServerInfoRequest;
import com.nomis.shared.response.ServerInfoResponse;
import com.nomis.shared.response.ServerLogOptionResponse;
import com.nomis.shared.response.ServerStatusResponse;
import com.nomis.util.ResourcesUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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

  @Override
  public ServerStatusResponse getServerStatus() throws IOException {
    return objectMapper.readValue(ResourcesUtil.getInstance()
        .getResource("ServerStatus.json"), ServerStatusResponse.class);
  }

  @Override
  public ServerInfoResponse getServerInfo(ServerInfoRequest serverInfoRequest) {
    ServerInfoResponse serverInfoResponse = new ServerInfoResponse();
    List<ServerInfo> serverInfoList = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      ServerInfo serverInfo = new ServerInfo();
      serverInfo.setKey("Key for ID = " + serverInfoRequest.getId());
      serverInfo.setValue(RandomStringUtils.randomAlphabetic(20));
      serverInfoList.add(serverInfo);
    }
    serverInfoResponse.setServerInfoList(serverInfoList);
    return serverInfoResponse;
  }

  @Override
  public ServerLogOptionResponse getServerLogOption() throws IOException {
    return objectMapper.readValue(ResourcesUtil.getInstance()
        .getResource("ServerLogOption.json"), ServerLogOptionResponse.class);
  }
}
