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
import com.nomis.client.security.LoggedInGatekeeper;
import com.nomis.client.widget.serverInfo.ServerInfoWidget;
import com.nomis.shared.model.ServerInfo;
import com.nomis.shared.response.ServerInfoResponse;
import java.util.List;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

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
  private HomeConstants homeConstants;

  @Inject
  private ServerService serverService;

  public static final Slot SLOT_SERVER_CONTENT = new Slot();
  public static final Slot SLOT_CLUSTER_CONTENT = new Slot();

  @Inject
  HomePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
    super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    serverService.serverInfo(new MethodCallback<ServerInfoResponse>() {
      @Override
      public void onFailure(Method method, Throwable exception) {
        MessageEvent.fire(HomePresenter.this, homeConstants.serverInfoError());
      }

      @Override
      public void onSuccess(Method method, ServerInfoResponse response) {
        addServerInfo(response.getServerInfoList());
        MessageEvent.fire(HomePresenter.this, homeConstants.serverInfoSuccess());
      }
    });
    super.onBind();
  }

  private void addServerInfo(List<ServerInfo> serverInfoList) {
    serverInfoList.forEach(serverInfo -> {
      ServerInfoWidget serverInfoWidget = serverInfoWidgetProvider.get();
      serverInfoWidget.setServerInfo(serverInfo);
      switch (serverInfo.getServerType()) {
        case SERVER:
          addToSlot(SLOT_SERVER_CONTENT, serverInfoWidget);
          break;
        case CLUSTER:
          addToSlot(SLOT_CLUSTER_CONTENT, serverInfoWidget);
          break;
        default:
          break;
      }
    });
  }
}
