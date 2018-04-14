package com.nomis.client.widget.serverInfo;

import com.google.gwt.resources.client.DataResource;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nomis.client.css.QartalPortalBundle;
import com.nomis.shared.model.ServerInfo;
import com.nomis.shared.model.ServerStatus;

/**
 * ServerInfoWidget.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public class ServerInfoWidget extends PresenterWidget<ServerInfoWidget.MyView> implements ServerInfoUiHandlers {

  interface MyView extends View, HasUiHandlers<ServerInfoUiHandlers> {

    void setText(String name, String wsUrl);

    void setImage(DataResource image);
  }

  @Inject
  private QartalPortalBundle qartalPortalBundle;

  private ServerInfo serverInfo;

  @Inject
  ServerInfoWidget(EventBus eventBus, MyView view) {
    super(eventBus, view);
    getView().setUiHandlers(this);
  }

  @Override
  public void setServerInfo(ServerInfo serverInfo) {
    this.serverInfo = serverInfo;
    setImage(serverInfo.getServerStatus());
    getView().setText(serverInfo.getName(), serverInfo.getWebSocketUrl());
  }

  private void setImage(ServerStatus serverStatus) {
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
  }
}
