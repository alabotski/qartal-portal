package com.nomis.client.application.login;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * ErrorView.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView {

  interface Binder extends UiBinder<Widget, LoginView> {

  }

  @Inject
  LoginView(LoginView.Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }


}
