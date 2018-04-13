package com.nomis.client.application.error;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * ErrorView.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class ErrorView extends ViewWithUiHandlers<ErrorUiHandlers> implements ErrorPresenter.MyView {

  interface Binder extends UiBinder<MaterialPanel, ErrorView> {

  }

  @UiField
  MaterialButton btnBack;

  @Inject
  ErrorView(ErrorView.Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @UiHandler("btnBack")
  void onLogoutClicked(ClickEvent event) {
    getUiHandlers().goBack();
  }

}
