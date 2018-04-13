package com.nomis.client.application.login;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * LoginModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class LoginModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class, LoginPresenter.MyProxy.class);

    bind(LoginConstants.class).asEagerSingleton();
  }

}
