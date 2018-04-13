package com.nomis.client.application.error;

import com.google.gwt.i18n.client.Constants;

/**
 * LoginConstants.
 *
 * @author Aliaksei Labotski.
 * @since 4/6/18.
 */
public interface ErrorConstants extends Constants {

  @DefaultStringValue("errorCaption")
  String errorCaption();

  @DefaultStringValue("errorFooter")
  String errorFooter();

  @DefaultStringValue("goBack")
  String goBack();

}
