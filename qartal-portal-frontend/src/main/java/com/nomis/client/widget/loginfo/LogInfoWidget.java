package com.nomis.client.widget.loginfo;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nomis.client.event.ClearEvent;
import com.nomis.client.event.ClearHandler;
import com.nomis.client.event.LogInfoEvent;
import com.nomis.client.event.LogInfoHandler;
import gwt.material.design.addins.client.richeditor.MaterialRichEditor;

/**
 * LogInfoWidget.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class LogInfoWidget extends PresenterWidget<LogInfoWidget.MyView> implements LogInfoUiHandlers, ClearHandler,
    LogInfoHandler {

  interface MyView extends View, HasUiHandlers<LogInfoUiHandlers> {

    MaterialRichEditor getLogInfo();
  }

  @Inject
  LogInfoWidget(EventBus eventBus, LogInfoWidget.MyView view) {
    super(eventBus, view);
    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    addRegisteredHandler(ClearEvent.TYPE, this);
    addRegisteredHandler(LogInfoEvent.TYPE, this);
    super.onBind();
  }

  @Override
  public void onClear(ClearEvent event) {
    getView().getLogInfo()
        .reset();
  }

  @Override
  public void onLogRow(LogInfoEvent event) {
    getView().getLogInfo()
        .setHTML(event.getLogRow() + getView().getLogInfo()
            .getHTML());
  }

}
