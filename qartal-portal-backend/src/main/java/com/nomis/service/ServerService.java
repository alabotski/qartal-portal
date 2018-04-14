package com.nomis.service;

import com.nomis.shared.response.ServerInfoResponse;
import java.io.IOException;

/**
 * ServerService.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public interface ServerService {

  ServerInfoResponse getServerInfo() throws IOException;
}
