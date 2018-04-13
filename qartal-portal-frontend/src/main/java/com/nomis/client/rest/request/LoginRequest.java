package com.nomis.client.rest.request;

import javax.validation.constraints.NotNull;

/**
 * LoginRequest.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class LoginRequest {

  @NotNull
  private String login;
  @NotNull
  private String password;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
