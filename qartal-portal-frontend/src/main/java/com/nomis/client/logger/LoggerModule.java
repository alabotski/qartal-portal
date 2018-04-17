package com.nomis.client.logger;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * LoggerModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/17/18.
 */
public class LoggerModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bind(JsonRemoteLogHandler.class).asEagerSingleton();
  }

}
