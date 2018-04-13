package com.nomis.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * LoadingHandler.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public interface LoadingHandler extends EventHandler {

  void onLoading(LoadingEvent event);
}
