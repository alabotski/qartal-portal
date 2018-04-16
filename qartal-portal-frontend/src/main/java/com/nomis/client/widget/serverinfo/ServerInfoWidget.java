package com.nomis.client.widget.serverinfo;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nomis.client.event.MessageEvent;
import com.nomis.client.event.ShowInfoEvent;
import com.nomis.client.event.ShowInfoHandler;
import com.nomis.client.rest.ServerService;
import com.nomis.shared.model.ServerInfo;
import com.nomis.shared.request.ServerInfoRequest;
import com.nomis.shared.response.ServerInfoResponse;
import gwt.material.design.client.ui.table.MaterialDataTable;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * ServerInfoWidget.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class ServerInfoWidget extends PresenterWidget<ServerInfoWidget.MyView> implements ServerInfoUiHandlers,
    ShowInfoHandler {

  interface MyView extends View, HasUiHandlers<ServerInfoUiHandlers> {

    void addColumnKey(String caption);

    void addColumnValue(String caption);

    void setTableTitle(String title);

    MaterialDataTable<ServerInfo> getServerInfo();

  }

  @Inject
  private ServerInfoConstants serverInfoConstants;

  @Inject
  private ServerService serverService;

  @Inject
  ServerInfoWidget(EventBus eventBus, ServerInfoWidget.MyView view) {
    super(eventBus, view);
    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    getView().addColumnKey(serverInfoConstants.keyCaption());
    getView().addColumnValue(serverInfoConstants.valueCaption());
    getView().setTableTitle(serverInfoConstants.serverInfoHeader());

    addRegisteredHandler(ShowInfoEvent.TYPE, this);
    super.onBind();
  }

  @Override
  public void onShowInfo(ShowInfoEvent event) {
    ServerInfoRequest serverInfoRequest = new ServerInfoRequest();
    serverInfoRequest.setId(event.getId());

    serverService.serverInfo(serverInfoRequest, new MethodCallback<ServerInfoResponse>() {
      @Override
      public void onFailure(Method method, Throwable exception) {
        MessageEvent.fire(ServerInfoWidget.this, serverInfoConstants.serverInfoError());
      }

      @Override
      public void onSuccess(Method method, ServerInfoResponse response) {
        getView().getServerInfo()
            .setRowData(0, response.getServerInfoList());
        MessageEvent.fire(ServerInfoWidget.this, serverInfoConstants.serverInfoSuccess());
      }
    });
  }

}
