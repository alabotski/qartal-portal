package com.nomis.client.application.error;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * ErrorView.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class ErrorView extends ViewWithUiHandlers<ErrorUiHandlers> implements ErrorPresenter.MyView {

  interface Binder extends UiBinder<Widget, ErrorView> {

  }

  @Inject
  ErrorView(ErrorView.Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }


}
