package com.nomis.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * ClearEvent.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public class ClearEvent extends GwtEvent<ClearHandler> {

  public static final Type<ClearHandler> TYPE = new Type<ClearHandler>();

  public Type<ClearHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ClearHandler handler) {
    handler.onClear(this);
  }


  public static void fire(HasHandlers source) {
    source.fireEvent(new ClearEvent());
  }
}
