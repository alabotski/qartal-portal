package com.nomis.client.application;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.LockInteractionEvent;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.nomis.client.event.LoadingEvent;
import com.nomis.client.event.LoadingHandler;
import com.nomis.client.place.NameTokens;
import com.nomis.client.widget.loading.LoadingWidget;

/**
 * ApplicationPresenter.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class ApplicationPresenter extends
    Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> implements ApplicationUiHandlers,
    LoadingHandler {

  @ProxyStandard
  @NameToken(NameTokens.home)
  interface MyProxy extends ProxyPlace<ApplicationPresenter> {

  }

  interface MyView extends View, HasUiHandlers<ApplicationUiHandlers> {

  }

  public static final SingleSlot SLOT_LOADING_CONTENT = new SingleSlot();
  public static final NestedSlot SLOT_MAIN_CONTENT = new NestedSlot();

  @Inject
  LoadingWidget loadingWidget;

  private final PlaceManager placeManager;

  @Inject
  ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager) {
    super(eventBus, view, proxy, RevealType.Root);
    this.placeManager = placeManager;
    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    addRegisteredHandler(LoadingEvent.TYPE, this);
  }

  @ProxyEvent
  public void onLockInteraction(LockInteractionEvent event) {
    LoadingEvent.fire(this, event.shouldLock());
  }

  @Override
  public void logout() {
    LoadingEvent.fire(this, true);
  }

  @Override
  public void onLoading(LoadingEvent event) {
    setInSlot(SLOT_LOADING_CONTENT, loadingWidget);
  }

}
