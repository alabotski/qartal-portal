package com.nomis.shared.model;

/**
 * ServerInfo.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public class ServerInfo {

  private String name;
  private ServerType serverType;
  private String webSocketUrl;
  private ServerStatus serverStatus;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServerType getServerType() {
    return serverType;
  }

  public void setServerType(ServerType serverType) {
    this.serverType = serverType;
  }

  public String getWebSocketUrl() {
    return webSocketUrl;
  }

  public void setWebSocketUrl(String webSocketUrl) {
    this.webSocketUrl = webSocketUrl;
  }

  public ServerStatus getServerStatus() {
    return serverStatus;
  }

  public void setServerStatus(ServerStatus serverStatus) {
    this.serverStatus = serverStatus;
  }
}
