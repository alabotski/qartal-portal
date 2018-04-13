package com.nomis.client.rest.response;

/**
 * LoginResponse.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class LoginResponse {

  private boolean authorization;

  public boolean isAuthorization() {
    return authorization;
  }

  public void setAuthorization(boolean authorization) {
    this.authorization = authorization;
  }
}
