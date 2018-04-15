package com.nomis.client.rest;

import com.nomis.shared.request.ServerInfoRequest;
import com.nomis.shared.response.ServerInfoResponse;
import com.nomis.shared.response.ServerStatusResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
  @Path("/server/serverStatus")
  void serverStatus(MethodCallback<ServerStatusResponse> callback);

  @POST
  @Path("/server/serverInfo")
  void serverInfo(ServerInfoRequest serverInfoRequest, MethodCallback<ServerInfoResponse> callback);
}
