package com.nomis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomis.shared.response.ServerInfoResponse;
import com.nomis.util.ResourcesUtil;
import java.io.IOException;
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

  @Override
  public ServerInfoResponse getServerInfo() throws IOException {
    return objectMapper.readValue(ResourcesUtil.getInstance()
        .getResource("ServerInfo.json"), ServerInfoResponse.class);
  }
}
