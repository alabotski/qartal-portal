package com.nomis.client.rest;

import com.nomis.shared.response.ServerInfoResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

/**
 * ServerService.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public interface ServerService extends RestService {

  @GET
  @Path("/server/serverstatus")
  void serverInfo(MethodCallback<ServerInfoResponse> callback);
}
