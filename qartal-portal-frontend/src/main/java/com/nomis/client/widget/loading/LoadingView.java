package com.nomis.client.widget.loading;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * LoadingView.
 *
 * @author Aliaksei Labotski.
 * @since 4/6/18.
 */
public class LoadingView extends ViewImpl implements LoadingWidget.MyView {

  interface Binder extends UiBinder<MaterialPanel, LoadingView> {

  }

  @UiField
  MaterialPanel loadingPanel;

  @Inject
  LoadingView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

}
