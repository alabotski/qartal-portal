package com.nomis.client.widget.logoption;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialCollectionItem;

/**
 * LogInfoView.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class LogOptionView extends ViewWithUiHandlers<LogOptionUiHandlers> implements LogOptionWidget.MyView {

  interface Binder extends UiBinder<MaterialCollectionItem, LogOptionView> {

  }

  @Inject
  LogOptionView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

}
