package com.nomis.client.model;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * ModelModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public class ModelModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bind(Person.class).asEagerSingleton();
  }

}
