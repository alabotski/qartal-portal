package com.nomis.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * HomeView.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {

  interface Binder extends UiBinder<MaterialPanel, HomeView> {

  }

  @Inject
  HomeView(HomeView.Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }


}
