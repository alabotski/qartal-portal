package com.nomis.service;

import com.nomis.shared.request.ServerInfoRequest;
import com.nomis.shared.response.ServerInfoResponse;
import com.nomis.shared.response.ServerLogOptionResponse;
import com.nomis.shared.response.ServerStatusResponse;
import java.io.IOException;

/**
 * ServerService.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public interface ServerService {

  ServerStatusResponse getServerStatus() throws IOException;

  ServerInfoResponse getServerInfo(ServerInfoRequest serverInfoRequest);

  ServerLogOptionResponse getServerLogOption() throws IOException;
}
