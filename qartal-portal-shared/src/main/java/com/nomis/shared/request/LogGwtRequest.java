package com.nomis.shared.request;

import javax.validation.constraints.NotNull;

/**
 * LogGwtRequest.
 *
 * @author Aliaksei Labotski.
 * @since 4/17/18.
 */
public class LogGwtRequest {

  @NotNull
  private String level;
  @NotNull
  private String message;
  private Throwable throwable;

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public void setThrowable(Throwable throwable) {
    this.throwable = throwable;
  }
}
