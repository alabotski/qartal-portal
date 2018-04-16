package com.nomis.client.widget.loginfo;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.addins.client.richeditor.MaterialRichEditor;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * LogInfoView.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class LogInfoView extends ViewWithUiHandlers<LogInfoUiHandlers> implements LogInfoWidget.MyView {

  interface Binder extends UiBinder<MaterialPanel, LogInfoView> {

  }

  @UiField
  MaterialRichEditor logInfo;

  @Inject
  LogInfoView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public MaterialRichEditor getLogInfo() {
    return logInfo;
  }

}
