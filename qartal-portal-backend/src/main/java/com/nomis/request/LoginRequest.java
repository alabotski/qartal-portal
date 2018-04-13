package com.nomis.request;

import javax.validation.constraints.NotNull;
import lombok.Data;


/**
 * LoginRequest.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
@Data
public class LoginRequest {

  @NotNull
  private String login;
  @NotNull
  private String password;

}
