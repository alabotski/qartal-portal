package com.nomis.client.widget.serverinfo;

import com.google.gwt.i18n.client.Constants;

/**
 * ServerInfoConstants.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public interface ServerInfoConstants extends Constants {

  @DefaultStringValue("serverInfoHeader")
  String serverInfoHeader();

  @DefaultStringValue("keyCaption")
  String keyCaption();

  @DefaultStringValue("valueCaption")
  String valueCaption();

  @DefaultStringValue("serverInfoSuccess")
  String serverInfoSuccess();

  @DefaultStringValue("serverInfoError")
  String serverInfoError();
}
