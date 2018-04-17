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
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.nomis.client.application.ApplicationPresenter;
import com.nomis.client.event.MessageEvent;
import com.nomis.client.place.NameTokens;
import com.nomis.client.rest.ServerService;
import com.nomis.client.rest.codec.ServerStatusResponseCodec;
import com.nomis.client.security.LoggedInGatekeeper;
import com.nomis.client.widget.loginfo.LogInfoWidget;
import com.nomis.client.widget.logoption.LogOptionWidget;
import com.nomis.client.widget.serverinfo.ServerInfoWidget;
import com.nomis.client.widget.serverstatus.ServerStatusWidget;
import com.nomis.shared.model.ServerStatusInfo;
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
  private Provider<ServerStatusWidget> serverStatusWidgetProvider;

  @Inject
  private ServerStatusResponseCodec serverStatusResponseCodec;

  @Inject
  private HomeConstants homeConstants;

  @Inject
  private ServerService serverService;

  @Inject
  private ServerInfoWidget serverInfoWidget;

  @Inject
  private LogOptionWidget logOptionWidget;

  @Inject
  private LogInfoWidget logInfoWidget;

  private WebSocket wsServerStatus;

  private List<ServerStatusWidget> serverList;
  private List<ServerStatusWidget> clusterList;

  public static final Slot SLOT_SERVER_CONTENT = new Slot();
  public static final Slot SLOT_CLUSTER_CONTENT = new Slot();
  public static final SingleSlot SLOT_SERVER_INFO_CONTENT = new SingleSlot();
  public static final SingleSlot SLOT_LOG_OPTION_CONTENT = new SingleSlot();
  public static final SingleSlot SLOT_LOG_INFO_CONTENT = new SingleSlot();

  @Inject
  HomePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
    super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
    getView().setUiHandlers(this);

    serverList = new LinkedList<>();
    clusterList = new LinkedList<>();
  }

  @Override
  protected void onBind() {
    setInSlot(SLOT_SERVER_INFO_CONTENT, serverInfoWidget);
    setInSlot(SLOT_LOG_OPTION_CONTENT, logOptionWidget);
    setInSlot(SLOT_LOG_INFO_CONTENT, logInfoWidget);
    super.onBind();
  }

  @Override
  protected void onReveal() {
    serverService.serverStatus(new MethodCallback<ServerStatusResponse>() {
      @Override
      public void onFailure(Method method, Throwable exception) {
        MessageEvent.fire(HomePresenter.this, homeConstants.serverStatusError());
      }

      @Override
      public void onSuccess(Method method, ServerStatusResponse response) {
        addServerInfo(response.getServerStatusList());
        wsServerStatus = WebSocket.newWebSocketIfSupported();
        if (null != wsServerStatus) {
          wsServerStatus.setListener(new WebSocketListenerAdapter() {
            @Override
            public void onMessage(final WebSocket webSocket, final String data) {
              ServerStatusResponse serverStatusResponse = serverStatusResponseCodec.decode(data);
              updateServerStatus(serverStatusResponse);
            }
          });
          wsServerStatus.connect(response.getWebSocketUrl());
        }
        MessageEvent.fire(HomePresenter.this, homeConstants.serverStatusSuccess());
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

  private void addServerInfo(List<ServerStatusInfo> serverStatusInfoList) {
    //    serverList.clear();
    //    clusterList.clear();
    //    clearSlot(SLOT_SERVER_CONTENT);
    //    clearSlot(SLOT_CLUSTER_CONTENT);

    serverStatusInfoList.forEach(serverStatusInfo -> {
      ServerStatusWidget serverStatusWidget = serverStatusWidgetProvider.get();
      serverStatusWidget.setServerStatusInfo(serverStatusInfo);
      switch (serverStatusInfo.getServerType()) {
        case SERVER:
          //          serverStatusWidget.setWidth("100%");
          addToSlot(SLOT_SERVER_CONTENT, serverInfoWidget);
          serverList.add(serverStatusWidget);
          break;
        case CLUSTER:
          serverStatusWidget.setGrid("s12 m12 l12");
          addToSlot(SLOT_CLUSTER_CONTENT, serverInfoWidget);
          clusterList.add(serverStatusWidget);
          break;
        default:
          break;
      }
    });
  }

  private void updateServerStatus(ServerStatusResponse serverStatusResponse) {
    serverStatusResponse.getServerStatusList()
        .forEach(serverInfo -> {
          List<ServerStatusWidget> serverInfoWidgetList = new LinkedList<>();
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
