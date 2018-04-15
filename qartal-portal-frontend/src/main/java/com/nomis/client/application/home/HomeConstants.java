package com.nomis.client.application.home;

import com.google.gwt.i18n.client.Constants;

/**
 * HomeConstants.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public interface HomeConstants extends Constants {

  @DefaultStringValue("serverStatusSuccess")
  String serverStatusSuccess();

  @DefaultStringValue("serverStatusError")
  String serverStatusError();

  @DefaultStringValue("serverHeaderText")
  String serverHeaderText();

  @DefaultStringValue("clusterHeaderText")
  String clusterHeaderText();

  @DefaultStringValue("serverInfo")
  String serverInfo();

  @DefaultStringValue("serverLog")
  String serverLog();
}
