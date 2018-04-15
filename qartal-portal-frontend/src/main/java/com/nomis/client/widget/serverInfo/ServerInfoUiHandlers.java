package com.nomis.client.widget.serverInfo;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nomis.shared.model.ServerInfo;
import com.nomis.shared.model.ServerStatus;

/**
 * ServerInfoUiHandlers.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public interface ServerInfoUiHandlers extends UiHandlers {

  void setServerInfo(ServerInfo serverInfo);

  void getServerInfo();

  void setServerStatus(ServerStatus serverStatus);

  int getServerId();
}
