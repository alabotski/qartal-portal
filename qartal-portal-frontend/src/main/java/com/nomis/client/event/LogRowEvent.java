package com.nomis.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * LogRowEvent.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public class LogRowEvent extends GwtEvent<LogRowHandler> {

  public static final Type<LogRowHandler> TYPE = new Type<LogRowHandler>();

  public Type<LogRowHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(LogRowHandler handler) {
    handler.onLogRow(this);
  }

  private String logRow;

  LogRowEvent(String logRow) {
    this.logRow = logRow;
  }

  public static void fire(HasHandlers source, String logRow) {
    source.fireEvent(new LogRowEvent(logRow));
  }

  public String getLogRow() {
    return logRow;
  }
}
