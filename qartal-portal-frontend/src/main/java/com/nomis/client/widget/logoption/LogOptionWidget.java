package com.nomis.client.widget.logoption;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nomis.client.event.ClearEvent;
import com.nomis.client.event.LogOptionEvent;
import com.nomis.client.event.LogOptionHandler;
import com.nomis.client.event.MessageEvent;
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
public class LogOptionWidget extends PresenterWidget<LogOptionWidget.MyView> implements LogOptionUiHandlers,
    LogOptionHandler {

  interface MyView extends View, HasUiHandlers<LogOptionUiHandlers> {

    void setLevel(List<String> levelList);

    void resetLogLevel();
  }

  @Inject
  private LogOptionConstants logOptionConstants;

  @Inject
  LogOptionWidget(EventBus eventBus, LogOptionWidget.MyView view) {
    super(eventBus, view);
    getView().setUiHandlers(this);
  }

  private LogLevel logLevel = LogLevel.ALL;

  @Override
  protected void onBind() {
    List<String> levelList = Stream.of(LogLevel.values())
        .map(Enum::name)
        .collect(Collectors.toList());
    getView().setLevel(levelList);

    addRegisteredHandler(LogOptionEvent.TYPE, this);
    super.onBind();
  }

  @Override
  public void show() {
    ClearEvent.fire(this);
  }

  @Override
  public void aplay(String singleValue) {
    logLevel = LogLevel.valueOf(singleValue);
    MessageEvent.fire(this, logLevel.name() + " " + logOptionConstants.setLogLevel());
  }

  @Override
  public void onLogOption(LogOptionEvent event) {
    logLevel = LogLevel.ALL;
    getView().resetLogLevel();
  }

}
