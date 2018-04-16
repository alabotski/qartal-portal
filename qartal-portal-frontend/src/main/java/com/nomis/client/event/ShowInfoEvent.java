package com.nomis.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * ShowInfoEvent.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class ShowInfoEvent extends GwtEvent<ShowInfoHandler> {

  public static final Type<ShowInfoHandler> TYPE = new Type<ShowInfoHandler>();

  public Type<ShowInfoHandler> getAssociatedType() {
    return TYPE;
  }

  private Integer id;

  ShowInfoEvent(Integer id) {
    this.id = id;
  }

  protected void dispatch(ShowInfoHandler handler) {
    handler.onShowInfo(this);
  }

  public static void fire(HasHandlers source, Integer id) {
    source.fireEvent(new ShowInfoEvent(id));
  }

  public Integer getId() {
    return id;
  }
}
