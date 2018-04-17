package com.nomis.rabbit.comunication.response;

import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;

/**
 * ServiceQueueResponse.
 *
 * @author Alexander Sokolov.
 */
@Slf4j
public abstract class ServiceQueueResponse {

  private String ipAddress;

  {
    try {
      this.ipAddress = InetAddress.getLocalHost()
          .getHostAddress();
    } catch (UnknownHostException ex) {
      log.error("ServiceQueueResponse", ex);
    }
  }


  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

}
