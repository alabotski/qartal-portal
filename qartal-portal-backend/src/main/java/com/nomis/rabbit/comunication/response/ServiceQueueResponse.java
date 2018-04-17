package com.nomis.rabbit.comunication.response;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Alexander Sokolov
 */
public abstract class ServiceQueueResponse {

  private String IP;

  {
    try {
      this.IP = InetAddress.getLocalHost()
          .getHostAddress();
    } catch (UnknownHostException ex) {
      throw new RuntimeException(ex);
    }
  }


  public String getIP() {
    return IP;
  }

  public void setIP(String IP) {
    this.IP = IP;
  }


}
