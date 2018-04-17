package com.nomis.shared.request;

/**
 * LogGwtRequest.
 *
 * @author Aliaksei Labotski.
 * @since 4/17/18.
 */
public class LogGwtRequest {

  private String level;
  private String message;
  private String sourceClassName;
  private String sourceMethodName;
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

  public String getSourceClassName() {
    return sourceClassName;
  }

  public void setSourceClassName(String sourceClassName) {
    this.sourceClassName = sourceClassName;
  }

  public String getSourceMethodName() {
    return sourceMethodName;
  }

  public void setSourceMethodName(String sourceMethodName) {
    this.sourceMethodName = sourceMethodName;
  }
}
