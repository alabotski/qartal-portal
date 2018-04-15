package com.nomis.client.widget.serverinfo;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * ServerInfoModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class ServerInfoModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(ServerInfoWidget.class, ServerInfoWidget.MyView.class, ServerInfoView.class);

    bind(ServerInfoConstants.class).asEagerSingleton();
  }

}
