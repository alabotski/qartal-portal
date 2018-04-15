package com.nomis.client.widget.serverinfo;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * ServerInfoWidget.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class ServerInfoWidget extends PresenterWidget<ServerInfoWidget.MyView> implements ServerInfoUiHandlers {

  interface MyView extends View, HasUiHandlers<ServerInfoUiHandlers> {

  }

  @Inject
  ServerInfoWidget(EventBus eventBus, ServerInfoWidget.MyView view) {
    super(eventBus, view);
  }
}
