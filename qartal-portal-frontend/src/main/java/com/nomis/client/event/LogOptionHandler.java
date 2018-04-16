package com.nomis.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * LogOptionHandler.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public interface LogOptionHandler extends EventHandler {

  void onLogOption(LogOptionEvent event);
}
