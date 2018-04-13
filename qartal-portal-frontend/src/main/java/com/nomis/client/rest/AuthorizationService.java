package com.nomis.client.rest;

import com.nomis.client.rest.request.LoginRequest;
import com.nomis.client.rest.response.LoginResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

/**
 * AuthorizationService.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public interface AuthorizationService extends RestService {

  @POST
  @Path("/authorization/login")
  void login(LoginRequest loginRequest, MethodCallback<LoginResponse> callback);

}
