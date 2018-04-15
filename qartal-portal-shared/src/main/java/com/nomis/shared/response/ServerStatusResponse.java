package com.nomis.shared.response;

import com.nomis.shared.model.ServerStatusInfo;
import java.util.List;

/**
 * ServerStatusResponse.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class ServerStatusResponse {

  private List<ServerStatusInfo> serverStatusList;
  private String webSocketUrl;


  public List<ServerStatusInfo> getServerStatusList() {
    return serverStatusList;
  }

  public void setServerStatusList(List<ServerStatusInfo> serverStatusList) {
    this.serverStatusList = serverStatusList;
  }

  public String getWebSocketUrl() {
    return webSocketUrl;
  }

  public void setWebSocketUrl(String webSocketUrl) {
    this.webSocketUrl = webSocketUrl;
  }
}
