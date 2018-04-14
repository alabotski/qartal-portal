package com.nomis.client.widget.serverInfo;

import com.google.gwt.resources.client.DataResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLabel;

/**
 * ServerInfoView.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public class ServerInfoView extends ViewWithUiHandlers<ServerInfoUiHandlers> implements ServerInfoWidget.MyView {

  interface Binder extends UiBinder<MaterialCollectionItem, ServerInfoView> {

  }

  @UiField
  MaterialImage serverStatus;

  @UiField
  MaterialLabel serverName;

  @UiField
  MaterialLabel serverWebSocketUrl;

  @Inject
  ServerInfoView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void setText(String name, String wsUrl) {
    serverName.setText(name);
    serverWebSocketUrl.setText(wsUrl);
  }

  @Override
  public void setImage(DataResource image) {
    serverStatus.setUrl(image.getSafeUri()
        .asString());
  }


}
