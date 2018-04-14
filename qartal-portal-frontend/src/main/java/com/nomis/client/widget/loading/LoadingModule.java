package com.nomis.client.widget.loading;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nomis.client.application.login.LoginConstants;

/**
 * LoadingModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class LoadingModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(LoadingWidget.class, LoadingWidget.MyView.class, LoadingView.class);

    bind(LoginConstants.class).asEagerSingleton();
  }
}
