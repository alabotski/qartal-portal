package com.nomis.client.rest.codec;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * CodecModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class CodecModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bind(ServerStatusResponseCodec.class).asEagerSingleton();
    bind(LogInfoResponseCodec.class).asEagerSingleton();
    bind(LogInfoRequestCodec.class).asEagerSingleton();
  }

}
