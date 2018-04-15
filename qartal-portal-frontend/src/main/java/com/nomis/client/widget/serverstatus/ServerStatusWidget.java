package com.nomis.client.widget.serverstatus;

import com.google.gwt.resources.client.DataResource;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nomis.client.css.QartalPortalBundle;
import com.nomis.client.event.MessageEvent;
import com.nomis.shared.model.ServerInfo;
import com.nomis.shared.model.ServerStatus;

/**
 * ServerStatusWidget.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public class ServerStatusWidget extends PresenterWidget<ServerStatusWidget.MyView> implements ServerStatusUiHandlers {

  interface MyView extends View, HasUiHandlers<ServerStatusUiHandlers> {

    void setImage(DataResource image);

    void setServerInfoName(String serverInfoName);

    void setServerStatusText(String serverStatusName);
  }

  @Inject
  private ServerStatusConstants serverStatusConstants;

  @Inject
  private QartalPortalBundle qartalPortalBundle;

  private ServerStatus serverStatus;
  private ServerInfo serverInfo;

  @Inject
  ServerStatusWidget(EventBus eventBus, MyView view) {
    super(eventBus, view);
    getView().setUiHandlers(this);
  }

  @Override
  public void setServerInfo(ServerInfo serverInfo) {
    this.serverInfo = serverInfo;
    setServerStatus(serverInfo.getServerStatus());
    getView().setServerInfoName(serverInfo.getName());
    getView().setServerStatusText(serverInfo.getServerStatus()
        .name());
  }

  @Override
  public void getServerInfo() {
    if (serverStatus == ServerStatus.DISABLE) {
      MessageEvent.fire(this, serverInfo.getServerType()
          .name() + " " + serverStatusConstants.isDisable());
    }
    //    else {
    //    }
  }

  @Override
  public void setServerStatus(ServerStatus serverStatus) {
    this.serverStatus = serverStatus;
    switch (serverStatus) {
      case ENABLE:
        getView().setImage(qartalPortalBundle.enable());
        break;
      case DISABLE:
        getView().setImage(qartalPortalBundle.disable());
        break;
      case RUNNING:
        getView().setImage(qartalPortalBundle.running());
        break;
      default:
        break;
    }
    getView().setServerStatusText(serverStatus.name());
  }

  @Override
  public int getServerId() {
    return serverInfo.getId();
  }
}
