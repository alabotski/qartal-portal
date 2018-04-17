package com.nomis.shared.model;

/**
 * ServerStatusInfo.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public class ServerStatusInfo {

  private Long id;
  private String name;
  private ServerType serverType;
  private ServerStatus serverStatus;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public ServerStatus getServerStatus() {
    return serverStatus;
  }

  public void setServerStatus(ServerStatus serverStatus) {
    this.serverStatus = serverStatus;
  }
}
