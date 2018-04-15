package com.nomis.client.widget.serverinfo;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialCollectionItem;

/**
 * ServerInfoView.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class ServerInfoView extends ViewWithUiHandlers<ServerInfoUiHandlers> implements ServerInfoWidget.MyView {

  interface Binder extends UiBinder<MaterialCollectionItem, ServerInfoView> {

  }

  @Inject
  ServerInfoView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

}
