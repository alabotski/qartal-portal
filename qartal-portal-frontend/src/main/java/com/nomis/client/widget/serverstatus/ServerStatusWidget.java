package com.nomis.client.widget.serverstatus;

import com.google.gwt.resources.client.DataResource;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nomis.client.css.QartalPortalBundle;
import com.nomis.client.event.LogOptionEvent;
import com.nomis.client.event.MessageEvent;
import com.nomis.client.event.ShowInfoEvent;
import com.nomis.shared.model.ServerStatus;
import com.nomis.shared.model.ServerStatusInfo;

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
  private ServerStatusInfo serverStatusInfo;

  @Inject
  ServerStatusWidget(EventBus eventBus, MyView view) {
    super(eventBus, view);
    getView().setUiHandlers(this);
  }

  @Override
  public void setServerStatusInfo(ServerStatusInfo serverStatusInfo) {
    this.serverStatusInfo = serverStatusInfo;
    setServerStatus(serverStatusInfo.getServerStatus());
    getView().setServerInfoName(serverStatusInfo.getName());
    getView().setServerStatusText(serverStatusInfo.getServerStatus()
        .name());
  }

  @Override
  public void getServerInfo() {
    switch (serverStatus) {
      case DISABLED:
        MessageEvent.fire(this, serverStatusInfo.getServerType()
            .name() + " " + serverStatusConstants.isDisable());
        break;
      case RUNNING:
        MessageEvent.fire(this, serverStatusInfo.getServerType()
            .name() + " " + serverStatusConstants.isRunning());
        break;
      case ENABLE:
        LogOptionEvent.fire(this, getServerId());
        ShowInfoEvent.fire(this, getServerId());
        break;
      default:
        break;
    }
  }

  @Override
  public void setServerStatus(ServerStatus serverStatus) {
    this.serverStatus = serverStatus;
    switch (serverStatus) {
      case ENABLE:
        getView().setImage(qartalPortalBundle.enable());
        break;
      case DISABLED:
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
    return serverStatusInfo.getId();
  }
}
