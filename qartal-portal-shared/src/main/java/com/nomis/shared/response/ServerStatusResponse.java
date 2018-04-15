package com.nomis.shared.response;

import com.nomis.shared.model.ServerInfo;
import java.util.List;

/**
 * ServerStatusResponse.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class ServerStatusResponse {

  private List<ServerInfo> serverInfoList;

  public List<ServerInfo> getServerInfoList() {
    return serverInfoList;
  }

  public void setServerInfoList(List<ServerInfo> serverInfoList) {
    this.serverInfoList = serverInfoList;
  }
}
