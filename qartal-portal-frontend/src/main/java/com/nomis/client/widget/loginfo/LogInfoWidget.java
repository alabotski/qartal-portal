package com.nomis.client.widget.loginfo;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * LogInfoWidget.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class LogInfoWidget extends PresenterWidget<LogInfoWidget.MyView> implements LogInfoUiHandlers {

  interface MyView extends View, HasUiHandlers<LogInfoUiHandlers> {

  }

  @Inject
  LogInfoWidget(EventBus eventBus, LogInfoWidget.MyView view) {
    super(eventBus, view);
  }
}
