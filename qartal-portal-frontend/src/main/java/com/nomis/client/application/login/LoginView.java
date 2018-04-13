package com.nomis.client.application.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.nomis.client.validator.LoginValidator;
import com.nomis.client.validator.PasswordValidator;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 * LoginView.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView {

  interface Binder extends UiBinder<Widget, LoginView> {

  }

  @UiField
  MaterialTextBox login;
  @UiField
  MaterialTextBox password;
  @UiField
  MaterialButton btnLogin;

  @Inject
  LoginView(LoginView.Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));

    login.addValidator(new LoginValidator());
    password.addValidator(new PasswordValidator());
  }

  @UiHandler("btnLogin")
  void onLogoutClicked(ClickEvent event) {
    getUiHandlers().login();
  }

  @Override
  public String getLogin() {
    return login.getText();
  }

  @Override
  public String getPassword() {
    return password.getText();
  }

  @Override
  public boolean isValid() {
    return login.validate() && password.validate();
  }


}
