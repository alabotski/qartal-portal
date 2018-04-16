package com.nomis.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * LogInfoHandler.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public interface LogInfoHandler extends EventHandler {

  void onLogRow(LogInfoEvent event);
}
