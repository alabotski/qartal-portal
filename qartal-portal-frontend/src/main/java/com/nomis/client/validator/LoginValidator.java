package com.nomis.client.validator;

import com.google.gwt.core.client.GWT;
import com.nomis.client.application.login.LoginConstants;
import gwt.material.design.client.base.validator.RegExValidator;

/**
 * LoginValidator.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class LoginValidator extends RegExValidator {

  private static LoginConstants loginConstants = GWT.create(LoginConstants.class);

  public LoginValidator() {
    super("\\$\\{[A-Za-z_0-9]+\\}", loginConstants.loginNotValid());
  }

  @Override
  public int getPriority() {
    return 0;
  }
}
