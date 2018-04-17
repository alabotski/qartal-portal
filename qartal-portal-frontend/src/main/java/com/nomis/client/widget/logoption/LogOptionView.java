package com.nomis.client.widget.logoption;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialPanel;
import java.util.List;

/**
 * LogInfoView.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class LogOptionView extends ViewWithUiHandlers<LogOptionUiHandlers> implements LogOptionWidget.MyView {

  interface Binder extends UiBinder<MaterialPanel, LogOptionView> {

  }

  @UiField
  MaterialComboBox<String> logLevel;

  @UiField
  MaterialButton btnShow;

  @UiField
  MaterialButton btnAplay;

  @Inject
  LogOptionView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void setLevel(List<String> levelList) {
    levelList.forEach(level -> logLevel.addItem(level, level));
  }

  @Override
  public void resetLogLevel() {
    logLevel.setSelectedIndex(0);
  }

  @Override
  public void setEnabled(boolean enabled) {
    logLevel.setEnabled(enabled);
    btnAplay.setEnabled(enabled);
  }

  @Override
  public void setShowBtnText(String caption) {
    btnShow.setText(caption);
  }

  @UiHandler("btnAplay")
  void onAplayClicked(ClickEvent event) {
    getUiHandlers().aplay(logLevel.getSingleValue());
  }

  @UiHandler("btnShow")
  void onShowClicked(ClickEvent event) {
    getUiHandlers().show();
  }
}
