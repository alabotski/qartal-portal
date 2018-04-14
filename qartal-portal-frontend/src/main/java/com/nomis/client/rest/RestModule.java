package com.nomis.client.rest;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * RestModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public class RestModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bind(AuthorizationService.class).asEagerSingleton();
    bind(ServerService.class).asEagerSingleton();
  }
}
