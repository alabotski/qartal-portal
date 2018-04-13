package com.nomis.client.application;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * ApplicationView.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class ApplicationView extends ViewWithUiHandlers<ApplicationUiHandlers> implements ApplicationPresenter.MyView {

  interface Binder extends UiBinder<MaterialPanel, ApplicationView> {

  }

  @UiField
  MaterialLink logout;
  @UiField
  MaterialContainer mainContent;
  @UiField
  MaterialPanel loadingContent;

  @Inject
  ApplicationView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));

    bindSlot(ApplicationPresenter.SLOT_MAIN_CONTENT, mainContent);
    bindSlot(ApplicationPresenter.SLOT_LOADING_CONTENT, loadingContent);
  }

  @UiHandler("logout")
  void onLogoutClicked(ClickEvent event) {
    getUiHandlers().logout();
  }
}
