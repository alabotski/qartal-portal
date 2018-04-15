package com.nomis.client.widget.logoption;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * LogInfoModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class LogOptionModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(LogOptionWidget.class, LogOptionWidget.MyView.class, LogOptionView.class);

    bind(LogOptionConstants.class).asEagerSingleton();
  }

}
