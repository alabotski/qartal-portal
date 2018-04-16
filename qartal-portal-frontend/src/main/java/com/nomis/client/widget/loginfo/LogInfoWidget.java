package com.nomis.client.widget.loginfo;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nomis.client.event.ClearEvent;
import com.nomis.client.event.ClearHandler;
import com.nomis.client.event.LogRowEvent;
import com.nomis.client.event.LogRowHandler;
import gwt.material.design.addins.client.richeditor.MaterialRichEditor;

/**
 * LogInfoWidget.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class LogInfoWidget extends PresenterWidget<LogInfoWidget.MyView> implements LogInfoUiHandlers, ClearHandler,
    LogRowHandler {

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
    addRegisteredHandler(LogRowEvent.TYPE, this);
    super.onBind();
  }

  @Override
  public void onClear(ClearEvent event) {
    getView().getLogInfo()
        .reset();
  }

  @Override
  public void onLogRow(LogRowEvent event) {
    getView().getLogInfo()
        .insertText(event.getLogRow());
  }

}
