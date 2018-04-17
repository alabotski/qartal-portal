package com.nomis.controller;

import static com.nomis.TestUtil.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomis.config.JacksonConfiguration;
import com.nomis.service.ServerService;
import com.nomis.shared.model.LogLevel;
import com.nomis.shared.model.ServerInfo;
import com.nomis.shared.request.ServerInfoRequest;
import com.nomis.shared.response.ServerInfoResponse;
import com.nomis.shared.response.ServerLogOptionResponse;
import com.nomis.shared.response.ServerStatusResponse;
import com.nomis.util.ResourcesUtil;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * ServerController.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WebAppContext.class})
@WebAppConfiguration
public class ServerControllerTest {

  private MockMvc mockMvc;

  @Mock
  private ServerService serverService;

  @InjectMocks
  private ServerController serverController;

  @Spy
  private ObjectMapper objectMapper = new JacksonConfiguration().objectMapper();

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(serverController)
        .build();
  }

  @Test
  public void should_serverStatus() throws Exception {
    ServerStatusResponse serverStatusResponse = objectMapper.readValue(ResourcesUtil.getInstance()
        .getResource("ServerStatus.json"), ServerStatusResponse.class);

    when(serverService.getServerStatus()).thenReturn(serverStatusResponse);

    mockMvc.perform(get("/server/serverStatus").contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.serverStatusList", hasSize(2)));

    verify(serverService, times(1)).getServerStatus();
    verifyNoMoreInteractions(serverService);
  }


  @Test
  public void should_serverInfo() throws Exception {
    List<ServerInfo> serverInfoList = new ArrayList<>();
    ServerInfo serverInfo = new ServerInfo();
    serverInfo.setKey("KEY");
    serverInfo.setValue("VALUE");
    serverInfoList.add(serverInfo);

    ServerInfoResponse serverInfoResponse = new ServerInfoResponse();
    serverInfoResponse.setServerInfoList(serverInfoList);
    when(serverService.getServerInfo()).thenReturn(serverInfoResponse);

    ServerInfoRequest serverInfoRequest = new ServerInfoRequest();
    serverInfoRequest.setId(1);

    mockMvc.perform(get("/server/serverInfo").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(asJsonString(serverInfoRequest))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.serverInfoList", hasSize(1)));

    verify(serverService, times(1)).getServerInfo();
    verifyNoMoreInteractions(serverService);
  }

  @Test
  public void should_serverLogOption() throws Exception {
    ServerLogOptionResponse serverLogOptionResponse = objectMapper.readValue(ResourcesUtil.getInstance()
        .getResource("ServerLogOption.json"), ServerLogOptionResponse.class);

    when(serverService.getServerLogOption()).thenReturn(serverLogOptionResponse);

    mockMvc.perform(get("/server/serverLogOption").contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.logLevel", is(LogLevel.ALL.name())));

    verify(serverService, times(1)).getServerLogOption();
    verifyNoMoreInteractions(serverService);
  }
}
