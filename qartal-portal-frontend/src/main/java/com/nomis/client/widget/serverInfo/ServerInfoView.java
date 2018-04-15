package com.nomis.client.widget.serverInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
  MaterialCollectionItem serverInfo;

  @UiField
  MaterialImage serverStatus;

  @UiField
  MaterialLabel serverName;

  @UiField
  MaterialLabel serverWebSocketUrl;

  @Inject
  ServerInfoView(Binder binder) {
    initWidget(binder.createAndBindUi(this));

    serverInfo.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        getUiHandlers().getServerInfo();
      }
    });
  }

  @Override
  public void setServerInfoName(String serverInfoName) {
    serverName.setText(serverInfoName);
  }

  @Override
  public void setImage(DataResource image) {
    serverStatus.setUrl(image.getSafeUri()
        .asString());
  }

  @Override
  public void setServerStatusText(String serverStatusName) {
    serverWebSocketUrl.setText(serverStatusName);
  }

}
