package com.nomis.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * LogOptionEvent.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public class LogOptionEvent extends GwtEvent<LogOptionHandler> {

  public static final Type<LogOptionHandler> TYPE = new Type<LogOptionHandler>();

  public Type<LogOptionHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(LogOptionHandler handler) {
    handler.onLogOption(this);
  }

  private Integer id;

  LogOptionEvent(Integer id) {
    this.id = id;
  }

  public static void fire(HasHandlers source, Integer id) {
    source.fireEvent(new LogOptionEvent(id));
  }

  public Integer getId() {
    return id;
  }
}
