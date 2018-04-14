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
  private int count;

  public List<ServerInfo> getServerInfoList() {
    return serverInfoList;
  }

  public void setServerInfoList(List<ServerInfo> serverInfoList) {
    this.serverInfoList = serverInfoList;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
