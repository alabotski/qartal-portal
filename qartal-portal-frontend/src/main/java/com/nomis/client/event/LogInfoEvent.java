package com.nomis.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * LogInfoEvent.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public class LogInfoEvent extends GwtEvent<LogInfoHandler> {

  public static final Type<LogInfoHandler> TYPE = new Type<LogInfoHandler>();

  public Type<LogInfoHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(LogInfoHandler handler) {
    handler.onLogRow(this);
  }

  private String logRow;

  LogInfoEvent(String logRow) {
    this.logRow = logRow;
  }

  public static void fire(HasHandlers source, String logRow) {
    source.fireEvent(new LogInfoEvent(logRow));
  }

  public String getLogRow() {
    return logRow;
  }
}
