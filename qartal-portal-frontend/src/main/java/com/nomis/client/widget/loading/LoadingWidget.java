package com.nomis.client.widget.loading;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * LoadingWidget.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class LoadingWidget extends PresenterWidget<LoadingWidget.MyView> {

  interface MyView extends View {

  }

  @Inject
  LoadingWidget(EventBus eventBus, MyView view) {
    super(eventBus, view);
  }

}
