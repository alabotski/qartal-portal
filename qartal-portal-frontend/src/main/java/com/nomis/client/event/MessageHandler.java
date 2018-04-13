package com.nomis.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * MessageHandler.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public interface MessageHandler extends EventHandler {

  void onMessage(MessageEvent event);
}
