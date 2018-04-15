package com.nomis.client.widget.loginfo;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * LogInfoModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class LogInfoModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(LogInfoWidget.class, LogInfoWidget.MyView.class, LogInfoView.class);

    bind(LogInfoConstants.class).asEagerSingleton();
  }

}
