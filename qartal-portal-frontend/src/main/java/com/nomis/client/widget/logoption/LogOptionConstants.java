package com.nomis.client.widget.logoption;

import com.google.gwt.i18n.client.Constants;

/**
 * LogInfoConstants.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public interface LogOptionConstants extends Constants {

  @DefaultStringValue("logLevel")
  String logLevel();

  @DefaultStringValue("aplay")
  String aplay();

  @DefaultStringValue("start")
  String start();

  @DefaultStringValue("stop")
  String stop();

  @DefaultStringValue("setLogLevel")
  String setLogLevel();

  @DefaultStringValue("serverLogOptionSuccess")
  String serverLogOptionSuccess();

  @DefaultStringValue("serverLogOptionError")
  String serverLogOptionError();
}
