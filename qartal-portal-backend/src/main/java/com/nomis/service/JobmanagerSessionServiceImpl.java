package com.nomis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
@Service
@Slf4j
public class JobmanagerSessionServiceImpl implements JobmanagerSessionService {


  @Inject
  JobManagerClientService jobManagerClientService;

  /**
   * The session mapper.
   */
  private List<WebSocketSession> sessionList = new ArrayList<>();

  public void addClientSession(WebSocketSession session) {
    sessionList.add(session);
  }

  @Inject
  private ObjectMapper mapper;

  @Override
  public void newMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
    session.sendMessage(new TextMessage(mapper.writeValueAsString(jobManagerClientService.getBaselineJobs())));
  }


  @Override
  public void removeSession(WebSocketSession session) {
    sessionList.remove(session);
  }
}
