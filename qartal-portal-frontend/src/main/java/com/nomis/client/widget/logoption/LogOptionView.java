package com.nomis.client.widget.logoption;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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

}
