package com.nomis.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

/**
 * SocketHandler.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@Slf4j
public class SocketHandler {

  private static Map<String, List<WebSocketSession>> sessionMap = new HashMap();

  public static final String SERVER1 = "SERVER1";
  public static final String SERVER2 = "SERVER2";
  public static final String SERVER3 = "SERVER3";
  public static final String SERVER4 = "SERVER4";
  public static final String SERVER5 = "SERVER5";
  public static final String SERVER6 = "SERVER6";
  public static final String SERVER7 = "SERVER7";
  public static final String SERVER8 = "SERVER8";
  public static final String SERVER9 = "SERVER9";
  public static final String SERVER10 = "SERVER10";

  static {
    List<WebSocketSession> webSocketSessionList1 = new ArrayList<>();
    List<WebSocketSession> webSocketSessionList2 = new ArrayList<>();
    List<WebSocketSession> webSocketSessionList3 = new ArrayList<>();
    List<WebSocketSession> webSocketSessionList4 = new ArrayList<>();
    List<WebSocketSession> webSocketSessionList5 = new ArrayList<>();
    List<WebSocketSession> webSocketSessionList6 = new ArrayList<>();
    List<WebSocketSession> webSocketSessionList7 = new ArrayList<>();
    List<WebSocketSession> webSocketSessionList8 = new ArrayList<>();
    List<WebSocketSession> webSocketSessionList9 = new ArrayList<>();
    List<WebSocketSession> webSocketSessionList10 = new ArrayList<>();

    sessionMap.put(SERVER1, webSocketSessionList1);
    sessionMap.put(SERVER2, webSocketSessionList2);
    sessionMap.put(SERVER3, webSocketSessionList3);
    sessionMap.put(SERVER4, webSocketSessionList4);
    sessionMap.put(SERVER5, webSocketSessionList5);
    sessionMap.put(SERVER6, webSocketSessionList6);
    sessionMap.put(SERVER7, webSocketSessionList7);
    sessionMap.put(SERVER8, webSocketSessionList8);
    sessionMap.put(SERVER9, webSocketSessionList9);
    sessionMap.put(SERVER10, webSocketSessionList10);
  }

  public static List<WebSocketSession> getWebSocketSession(String server) {
    //    log.info("SIZE = " + sessionMap.size());
    //    log.info(sessionMap.get(server) == null ? "NULL" : "NOT NULL");
    return sessionMap.get(server);
  }

  public static Collection<List<WebSocketSession>> getWebSocketSession() {
    return sessionMap.values();
  }
}
