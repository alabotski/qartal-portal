package com.nomis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomis.config.JacksonConfiguration;
import com.nomis.shared.response.ServerInfoResponse;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

  @InjectMocks
  private ServerServiceImpl serverServiceImpl;

  @Spy
  private ObjectMapper objectMapper = new JacksonConfiguration().objectMapper();

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void should_serverInfo() throws IOException {
    ServerInfoResponse serverInfoResponse = serverServiceImpl.getServerInfo();
    Assert.assertEquals(serverInfoResponse.getServerInfoList()
        .size(), 2);
  }
}
