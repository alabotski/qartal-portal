package com.nomis.controller;

import static com.nomis.TestUtil.asJsonString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
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
import com.nomis.shared.model.ServerInfo;
import com.nomis.shared.request.ServerInfoRequest;
import com.nomis.shared.response.ServerInfoResponse;
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
  ;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(serverController)
        .build();
  }

  @Test
  public void should_serverStatus() throws Exception {
    ServerStatusResponse serverStatusResponse = objectMapper.readValue(ResourcesUtil.getInstance()
        .getResource("ServerInfo.json"), ServerStatusResponse.class);

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
    ServerInfoRequest serverInfoRequest = new ServerInfoRequest();
    serverInfoRequest.setId(1);

    ServerInfoResponse serverInfoResponse = new ServerInfoResponse();
    List<ServerInfo> serverInfoList = new ArrayList<>();
    ServerInfo serverInfo = new ServerInfo();
    serverInfo.setKey("KEY");
    serverInfo.setValue("VALUE");
    serverInfoList.add(serverInfo);
    serverInfoResponse.setServerInfoList(serverInfoList);
    when(serverService.getServerInfo(any())).thenReturn(serverInfoResponse);

    mockMvc.perform(get("/server/serverInfo").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(asJsonString(serverInfoRequest))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.serverInfoList", hasSize(1)));

    verify(serverService, times(1)).getServerInfo(refEq(serverInfoRequest));
    verifyNoMoreInteractions(serverInfoRequest);
  }
}
