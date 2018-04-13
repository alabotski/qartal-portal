package com.nomis.client.place;

/**
 * NameTokens.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class NameTokens {

  private NameTokens() {
  }

  public static final String home = "home";
  public static final String error = "error";
  public static final String login = "login";

  public static String getHome() {
    return home;
  }

  public static String getError() {
    return error;
  }

  public static String getLogin() {
    return login;
  }
}
