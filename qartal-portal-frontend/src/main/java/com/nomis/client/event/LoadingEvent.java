package com.nomis.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * LoadingEvent.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class LoadingEvent extends GwtEvent<LoadingHandler> {

  public static Type<LoadingHandler> TYPE = new Type<LoadingHandler>();

  private boolean showLoading;

  public Type<LoadingHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(LoadingHandler handler) {
    handler.onLoading(this);
  }

  LoadingEvent(boolean showLoading) {
    this.showLoading = showLoading;
  }

  public static void fire(HasHandlers source, boolean showLoading) {
    source.fireEvent(new LoadingEvent(showLoading));
  }

  public boolean isShowLoading() {
    return showLoading;
  }
}

