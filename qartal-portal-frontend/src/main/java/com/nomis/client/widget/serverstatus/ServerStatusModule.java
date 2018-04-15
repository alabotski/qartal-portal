package com.nomis.client.widget.serverstatus;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * ServerStatusModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public class ServerStatusModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(ServerStatusWidget.class, ServerStatusWidget.MyView.class, ServerStatusView.class);

    bind(ServerStatusConstants.class).asEagerSingleton();
  }
}
