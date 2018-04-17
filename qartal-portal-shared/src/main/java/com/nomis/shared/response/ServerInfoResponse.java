package com.nomis.shared.response;

import com.nomis.shared.model.ServerInfo;
import java.util.List;

/**
 * ServerInfoResponse.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public class ServerInfoResponse {

  private List<ServerInfo> serverInfoList;
  private String webSocketUrl;

  public List<ServerInfo> getServerInfoList() {
    return serverInfoList;
  }

  public void setServerInfoList(List<ServerInfo> serverInfoList) {
    this.serverInfoList = serverInfoList;
  }

  public String getWebSocketUrl() {
    return webSocketUrl;
  }

  public void setWebSocketUrl(String webSocketUrl) {
    this.webSocketUrl = webSocketUrl;
  }
}
