package com.nomis.client.validator;

import com.google.gwt.core.client.GWT;
import com.nomis.client.application.login.LoginConstants;
import gwt.material.design.client.base.validator.RegExValidator;

/**
 * PasswordValidator.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class PasswordValidator extends RegExValidator {

  private static LoginConstants loginConstants = GWT.create(LoginConstants.class);

  public PasswordValidator() {
    super("\\$\\{[A-Za-z_0-9]+\\}", loginConstants.passwordNotValid());
  }

  @Override
  public int getPriority() {
    return 0;
  }
}
