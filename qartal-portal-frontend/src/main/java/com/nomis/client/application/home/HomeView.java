package com.nomis.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * HomeView.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {

  interface Binder extends UiBinder<Widget, HomeView> {

  }

  @Inject
  HomeView(HomeView.Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }


}
