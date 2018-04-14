package com.nomis.client.application.home;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.nomis.client.application.ApplicationPresenter;
import com.nomis.client.event.MessageEvent;
import com.nomis.client.place.NameTokens;
import com.nomis.client.rest.ServerService;
import com.nomis.client.security.LoggedInGatekeeper;
import com.nomis.shared.response.LoginResponse;
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
  private HomeConstants homeConstants;

  @Inject
  private ServerService serverService;

  @Inject
  HomePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
    super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    serverService.serverInfo(new MethodCallback<LoginResponse>() {
      @Override
      public void onFailure(Method method, Throwable exception) {
        MessageEvent.fire(HomePresenter.this, homeConstants.serverInfoError());
      }

      @Override
      public void onSuccess(Method method, LoginResponse response) {
        MessageEvent.fire(HomePresenter.this, homeConstants.serverInfoSuccess());
      }
    });
    super.onBind();
  }
}
