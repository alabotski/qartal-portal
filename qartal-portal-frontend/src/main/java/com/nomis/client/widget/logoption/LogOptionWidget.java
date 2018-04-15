package com.nomis.client.widget.logoption;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nomis.shared.model.LogLevel;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * LogInfoWidget.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class LogOptionWidget extends PresenterWidget<LogOptionWidget.MyView> implements LogOptionUiHandlers {

  interface MyView extends View, HasUiHandlers<LogOptionUiHandlers> {

    void setLevel(List<String> levelList);
  }

  @Inject
  LogOptionWidget(EventBus eventBus, LogOptionWidget.MyView view) {
    super(eventBus, view);
  }

  @Override
  protected void onBind() {
    List<String> levelList = Stream.of(LogLevel.values())
        .map(Enum::name)
        .collect(Collectors.toList());
    getView().setLevel(levelList);
    super.onBind();
  }
}
