package com.nomis.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nomis.client.application.login.LoginConstants;

/**
 * ApplicationModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class ApplicationModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
        ApplicationPresenter.MyProxy.class);

    bind(ApplicationConstants.class).asEagerSingleton();
  }
}
