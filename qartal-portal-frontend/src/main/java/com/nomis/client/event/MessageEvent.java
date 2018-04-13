package com.nomis.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * MessageEvent.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class MessageEvent extends GwtEvent<MessageHandler> {

  public static final Type<MessageHandler> TYPE = new Type<MessageHandler>();

  public Type<MessageHandler> getAssociatedType() {
    return TYPE;
  }

  private String message;

  protected void dispatch(MessageHandler handler) {
    handler.onMessage(this);
  }

  MessageEvent(String message) {
    this.message = message;
  }

  public static void fire(HasHandlers source, String message) {
    source.fireEvent(new MessageEvent(message));
  }

  public String getMessage() {
    return message;
  }
}
