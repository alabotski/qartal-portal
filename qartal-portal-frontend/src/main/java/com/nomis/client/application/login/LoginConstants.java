package com.nomis.client.application.login;

import com.google.gwt.i18n.client.Constants;

/**
 * LoginConstants.
 *
 * @author Aliaksei Labotski.
 * @since 4/6/18.
 */
public interface LoginConstants extends Constants {

  @DefaultStringValue("loginText")
  String loginText();

  @DefaultStringValue("passwordText")
  String passwordText();

  @DefaultStringValue("loginNotValid")
  String loginNotValid();

  @DefaultStringValue("passwordNotValid")
  String passwordNotValid();

  @DefaultStringValue("loginBtnText")
  String loginBtnText();

  @DefaultStringValue("authorizationSuccess")
  String authorizationSuccess();

  @DefaultStringValue("authorizationError")
  String authorizationError();

}
