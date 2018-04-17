package com.nomis.client.widget.serverstatus;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nomis.shared.model.ServerStatus;
import com.nomis.shared.model.ServerStatusInfo;

/**
 * ServerStatusUiHandlers.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public interface ServerStatusUiHandlers extends UiHandlers {

  void setServerStatusInfo(ServerStatusInfo serverStatusInfo);

  void getServerInfo();

  void setServerStatus(ServerStatus serverStatus);

  int getServerId();

  void setGrid(String grid);

  void setWidth(String width);
}
