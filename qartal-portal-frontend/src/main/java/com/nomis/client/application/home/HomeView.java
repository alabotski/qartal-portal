package com.nomis.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;

/**
 * HomeView.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {

  interface Binder extends UiBinder<MaterialPanel, HomeView> {

  }

  @UiField
  MaterialColumn serverCollection;

  @UiField
  MaterialColumn clusterCollection;

  @UiField
  MaterialRow serverInfo;

  @UiField
  MaterialRow logOption;

  @UiField
  MaterialRow logInfo;

  @Inject
  HomeView(HomeView.Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));

    bindSlot(HomePresenter.SLOT_SERVER_CONTENT, serverCollection);
    bindSlot(HomePresenter.SLOT_CLUSTER_CONTENT, clusterCollection);
    bindSlot(HomePresenter.SLOT_SERVER_INFO_CONTENT, serverInfo);
    bindSlot(HomePresenter.SLOT_LOG_OPTION_CONTENT, logOption);
    bindSlot(HomePresenter.SLOT_LOG_INFO_CONTENT, logInfo);
  }

}
