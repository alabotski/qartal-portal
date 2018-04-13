package com.nomis.client.application.error;

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
import com.nomis.client.application.ApplicationPresenter;
import com.nomis.client.place.NameTokens;

/**
 * ErrorPresenter.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class ErrorPresenter extends Presenter<ErrorPresenter.MyView, ErrorPresenter.MyProxy> implements
    ErrorUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.error)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<ErrorPresenter> {

  }

  public interface MyView extends View, HasUiHandlers<ErrorUiHandlers> {

  }

  private final PlaceManager placeManager;

  @Inject
  ErrorPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager) {
    super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
    this.placeManager = placeManager;
    getView().setUiHandlers(this);
  }
}
