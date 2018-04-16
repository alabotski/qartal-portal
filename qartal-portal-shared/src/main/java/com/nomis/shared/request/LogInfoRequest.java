package com.nomis.shared.request;

import com.nomis.shared.model.LogLevel;

/**
 * LogInfoRequest.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public class LogInfoRequest {

  private LogLevel logLevel;

  public LogLevel getLogLevel() {
    return logLevel;
  }

  public void setLogLevel(LogLevel logLevel) {
    this.logLevel = logLevel;
  }
}
