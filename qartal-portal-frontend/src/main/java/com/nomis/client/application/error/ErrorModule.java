package com.nomis.client.application.error;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * ErrorModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class ErrorModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenter(ErrorPresenter.class, ErrorPresenter.MyView.class, ErrorView.class, ErrorPresenter.MyProxy.class);
  }

}
