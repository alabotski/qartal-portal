package com.nomis.client.rest;

import com.nomis.shared.request.LogGwtRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

/**
 * LoggerService.
 *
 * @author Aliaksei Labotski.
 * @since 4/17/18.
 */
public interface LoggerService extends RestService {

  @POST
  @Path("/logger/log")
  void log(LogGwtRequest logGwtRequest, MethodCallback<Void> callback);

}
