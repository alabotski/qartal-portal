package com.nomis.shared.response;

import com.nomis.shared.model.LogLevel;
import javax.validation.constraints.NotNull;

/**
 * ServerLogOptionResponse.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public class ServerLogOptionResponse {

  @NotNull
  private LogLevel logLevel;
  @NotNull
  private String webSocketUrl;

  public LogLevel getLogLevel() {
    return logLevel;
  }

  public void setLogLevel(LogLevel logLevel) {
    this.logLevel = logLevel;
  }

  public String getWebSocketUrl() {
    return webSocketUrl;
  }

  public void setWebSocketUrl(String webSocketUrl) {
    this.webSocketUrl = webSocketUrl;
  }
}
