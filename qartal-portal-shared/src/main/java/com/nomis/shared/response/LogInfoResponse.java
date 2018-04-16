package com.nomis.shared.response;

import com.nomis.shared.model.LogLevel;

/**
 * LogInfoResponse.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public class LogInfoResponse {

  private LogLevel logLevel;
  private String currentTime;
  private String message;
  private String sessionId;

  public LogLevel getLogLevel() {
    return logLevel;
  }

  public void setLogLevel(LogLevel logLevel) {
    this.logLevel = logLevel;
  }

  public String getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(String currentTime) {
    this.currentTime = currentTime;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }
}
