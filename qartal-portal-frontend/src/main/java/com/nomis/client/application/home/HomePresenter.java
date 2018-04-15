package com.nomis.client.application.home;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.nomis.client.application.ApplicationPresenter;
import com.nomis.client.event.MessageEvent;
import com.nomis.client.place.NameTokens;
import com.nomis.client.rest.ServerService;
import com.nomis.client.rest.codec.ServerStatusCodec;
import com.nomis.client.security.LoggedInGatekeeper;
import com.nomis.client.widget.serverInfo.ServerInfoWidget;
import com.nomis.shared.model.ServerInfo;
import com.nomis.shared.response.ServerInfoResponse;
import com.nomis.shared.response.ServerStatusResponse;
import java.util.LinkedList;
import java.util.List;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.realityforge.gwt.websockets.client.WebSocket;
import org.realityforge.gwt.websockets.client.WebSocketListenerAdapter;

/**
 * HomePresenter.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements HomeUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.home)
  @UseGatekeeper(LoggedInGatekeeper.class)
  public interface MyProxy extends ProxyPlace<HomePresenter> {

  }

  public interface MyView extends View, HasUiHandlers<HomeUiHandlers> {

  }

  @Inject
  private Provider<ServerInfoWidget> serverInfoWidgetProvider;

  @Inject
  private ServerStatusCodec serverStatusCodec;

  @Inject
  private HomeConstants homeConstants;

  @Inject
  private ServerService serverService;

  private WebSocket wsServerStatus;

  private List<ServerInfoWidget> serverList;
  private List<ServerInfoWidget> clusterList;

  public static final Slot SLOT_SERVER_CONTENT = new Slot();
  public static final Slot SLOT_CLUSTER_CONTENT = new Slot();

  @Inject
  HomePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
    super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
    getView().setUiHandlers(this);

    serverList = new LinkedList<>();
    clusterList = new LinkedList<>();
  }

  @Override
  protected void onBind() {

    super.onBind();
  }

  @Override
  protected void onReveal() {
    serverService.serverInfo(new MethodCallback<ServerInfoResponse>() {
      @Override
      public void onFailure(Method method, Throwable exception) {
        MessageEvent.fire(HomePresenter.this, homeConstants.serverInfoError());
      }

      @Override
      public void onSuccess(Method method, ServerInfoResponse response) {
        addServerInfo(response.getServerInfoList());
        wsServerStatus = WebSocket.newWebSocketIfSupported();
        if (null != wsServerStatus) {
          wsServerStatus.setListener(new WebSocketListenerAdapter() {
            @Override
            public void onMessage(final WebSocket webSocket, final String data) {
              ServerStatusResponse serverStatusResponse = serverStatusCodec.decode(data);
              updateServerStatus(serverStatusResponse);
            }
          });
          wsServerStatus.connect(response.getWebSocketUrl());
        }
        MessageEvent.fire(HomePresenter.this, homeConstants.serverInfoSuccess());
      }
    });
    super.onReveal();
  }

  @Override
  protected void onReset() {
    if (wsServerStatus != null && wsServerStatus.isConnected()) {
      wsServerStatus.close();
    }
    super.onReset();
  }

  private void addServerInfo(List<ServerInfo> serverInfoList) {
    serverList.clear();
    clusterList.clear();

    serverInfoList.forEach(serverInfo -> {
      ServerInfoWidget serverInfoWidget = serverInfoWidgetProvider.get();
      serverInfoWidget.setServerInfo(serverInfo);
      switch (serverInfo.getServerType()) {
        case SERVER:
          addToSlot(SLOT_SERVER_CONTENT, serverInfoWidget);
          serverList.add(serverInfoWidget);
          break;
        case CLUSTER:
          addToSlot(SLOT_CLUSTER_CONTENT, serverInfoWidget);
          clusterList.add(serverInfoWidget);
          break;
        default:
          break;
      }
    });
  }

  private void updateServerStatus(ServerStatusResponse serverStatusResponse) {
    serverStatusResponse.getServerInfoList()
        .forEach(serverInfo -> {
          List<ServerInfoWidget> serverInfoWidgetList = new LinkedList<>();
          switch (serverInfo.getServerType()) {
            case SERVER:
              serverInfoWidgetList = serverList;
              break;
            case CLUSTER:
              serverInfoWidgetList = clusterList;
              break;
            default:
              break;
          }
          serverInfoWidgetList.forEach(serverInfoWidget -> {
            if (serverInfoWidget.getServerId() == serverInfo.getId()) {
              serverInfoWidget.setServerStatus(serverInfo.getServerStatus());
            }
          });
        });
  }
}
