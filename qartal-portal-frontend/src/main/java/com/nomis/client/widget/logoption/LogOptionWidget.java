package com.nomis.client.widget.logoption;

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
public class LogOptionWidget extends PresenterWidget<LogOptionWidget.MyView> implements LogOptionUiHandlers {

  interface MyView extends View, HasUiHandlers<LogOptionUiHandlers> {

  }

  @Inject
  LogOptionWidget(EventBus eventBus, LogOptionWidget.MyView view) {
    super(eventBus, view);
  }
}
