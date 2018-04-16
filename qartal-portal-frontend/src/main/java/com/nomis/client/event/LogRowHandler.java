package com.nomis.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * LogRowHandler.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public interface LogRowHandler extends EventHandler {

  void onLogRow(LogRowEvent event);
}
