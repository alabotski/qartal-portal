package com.nomis.client.widget.serverstatus;

import com.google.gwt.resources.client.DataResource;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nomis.client.css.QartalPortalBundle;
import com.nomis.client.event.LogOptionEvent;
import com.nomis.client.event.ShowInfoEvent;
import com.nomis.shared.model.ServerStatus;
import com.nomis.shared.model.ServerStatusInfo;
import gwt.material.design.client.constants.Color;

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

    void setServerStatusColor(Color color);

    void setGrid(String grid);

    void setWidth(String width);
  }

  //  @Inject
  //  private ServerStatusConstants serverStatusConstants;

  @Inject
  private QartalPortalBundle qartalPortalBundle;

  //  private ServerStatus serverStatus;
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
    LogOptionEvent.fire(this, getServerId());
    ShowInfoEvent.fire(this, getServerId());

    /*
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
    */
  }

  @Override
  public void setServerStatus(ServerStatus serverStatus) {
    //    this.serverStatus = serverStatus;
    switch (serverStatus) {
      case ENABLE:
        getView().setImage(qartalPortalBundle.enable());
        getView().setServerStatusColor(Color.GREEN);
        break;
      case DISABLED:
        getView().setImage(qartalPortalBundle.disabled());
        getView().setServerStatusColor(Color.RED);
        break;
      case RUNNING:
        getView().setImage(qartalPortalBundle.running());
        getView().setServerStatusColor(Color.BLUE);
        break;
      case NOT_ACTUAL:
        getView().setImage(qartalPortalBundle.notActual());
        getView().setServerStatusColor(Color.GREY);
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

  @Override
  public void setGrid(String grid) {
    getView().setGrid(grid);
  }

  @Override
  public void setWidth(String width) {
    getView().setWidth(width);
  }
}
