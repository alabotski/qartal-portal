package com.nomis.service;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomis.config.JacksonConfiguration;
import com.nomis.shared.model.LogLevel;
import com.nomis.shared.request.ServerInfoRequest;
import com.nomis.shared.response.ServerInfoResponse;
import com.nomis.shared.response.ServerLogOptionResponse;
import com.nomis.shared.response.ServerStatusResponse;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * ServerServiceTest.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class ServerServiceTest {

  @Mock
  private NodesService nodesService;

  @Mock
  private PropertyService propertyService;

  @InjectMocks
  private ServerServiceImpl serverServiceImpl;

  @Spy
  private ObjectMapper objectMapper = new JacksonConfiguration().objectMapper();

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void should_serverStatus() throws IOException {
    when(propertyService.getStatusSocketUrl()).thenReturn("TestWS");

    ServerStatusResponse serverStatusResponse = serverServiceImpl.getServerStatus();
    Assert.assertEquals(serverStatusResponse.getWebSocketUrl(), "TestWS");
    Assert.assertEquals(serverStatusResponse.getServerStatusList()
        .size(), 0);
  }


  @Test
  public void should_serverInfo() throws IOException {
    ServerInfoRequest serverInfoRequest = new ServerInfoRequest();
    serverInfoRequest.setId(1);

    ServerInfoResponse serverInfoResponse = serverServiceImpl.getServerInfo();
    Assert.assertNotNull(serverInfoResponse.getWebSocketUrl());
  }

  @Test
  public void should_serverLogOption() throws IOException {
    ServerLogOptionResponse serverLogOptionResponse = serverServiceImpl.getServerLogOption();
    Assert.assertEquals(serverLogOptionResponse.getLogLevel(), LogLevel.ALL);
  }
}
