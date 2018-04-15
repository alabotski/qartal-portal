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

  public List<ServerInfo> getServerInfoList() {
    return serverInfoList;
  }

  public void setServerInfoList(List<ServerInfo> serverInfoList) {
    this.serverInfoList = serverInfoList;
  }

}
