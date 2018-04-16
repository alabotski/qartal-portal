package com.nomis.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * ClearHandler.
 *
 * @author Aliaksei Labotski.
 * @since 4/16/18.
 */
public interface ClearHandler extends EventHandler {

  void onClear(ClearEvent event);
}
