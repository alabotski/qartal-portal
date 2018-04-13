package com.nomis.client.application.login;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.nomis.client.application.ApplicationPresenter;
import com.nomis.client.event.MessageEvent;
import com.nomis.client.model.Person;
import com.nomis.client.place.NameTokens;
import com.nomis.client.rest.AuthorizationService;
import com.nomis.shared.request.LoginRequest;
import com.nomis.shared.response.LoginResponse;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * LoginPresenter.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> implements
    LoginUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.login)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<LoginPresenter> {

  }

  public interface MyView extends View, HasUiHandlers<LoginUiHandlers> {

    String getLogin();

    String getPassword();
  }

  private AuthorizationService authorizationService = GWT.create(AuthorizationService.class);

  @Inject
  private Person person;

  @Inject
  private LoginConstants loginConstants;

  private final PlaceManager placeManager;

  @Inject
  LoginPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager) {
    super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
    this.placeManager = placeManager;
    getView().setUiHandlers(this);
  }

  @Override
  public void login() {
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setLogin(getView().getLogin());
    loginRequest.setPassword(getView().getPassword());

    authorizationService.login(loginRequest, new MethodCallback<LoginResponse>() {
      @Override
      public void onFailure(Method method, Throwable exception) {
        MessageEvent.fire(LoginPresenter.this, loginConstants.authorizationError());
      }

      @Override
      public void onSuccess(Method method, LoginResponse response) {
        if (response.isAuthorization()) {
          person.setAuthorization(true);
          MessageEvent.fire(LoginPresenter.this, loginConstants.authorizationSuccess());
          PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.getHome())
              .build();
          placeManager.revealPlace(placeRequest);
        } else {
          MessageEvent.fire(LoginPresenter.this, loginConstants.authorizationError());
        }
      }
    });
  }
}
