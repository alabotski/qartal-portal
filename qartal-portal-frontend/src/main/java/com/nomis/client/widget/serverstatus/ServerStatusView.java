package com.nomis.client.widget.serverstatus;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;

/**
 * ServerStatusView.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public class ServerStatusView extends ViewWithUiHandlers<ServerStatusUiHandlers> implements ServerStatusWidget.MyView {

  interface Binder extends UiBinder<MaterialColumn, ServerStatusView> {

  }

  @UiField
  MaterialColumn serverStatusWrapper;

  @UiField
  MaterialImage serverIcon;

  @UiField
  MaterialCardTitle serverName;

  @UiField
  MaterialLabel serverStatus;

  @UiField
  MaterialLink showInfo;

  @Inject
  ServerStatusView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void setServerInfoName(String serverInfoName) {
    serverName.setText(serverInfoName);
  }

  @Override
  public void setImage(DataResource image) {
    serverIcon.setUrl(image.getSafeUri()
        .asString());
  }

  @Override
  public void setServerStatusText(String serverStatusName) {
    serverStatus.setText(serverStatusName);
  }

  @Override
  public void setServerStatusColor(Color color) {
    serverStatus.setTextColor(color);
  }

  @Override
  public void setGrid(String grid) {
    serverStatusWrapper.setGrid(grid);
  }

  @Override
  public void setWidth(String width) {
    serverStatusWrapper.setWidth(width);
  }

  @UiHandler("showInfo")
  void onShowInfoClicked(ClickEvent event) {
    getUiHandlers().getServerInfo();
  }

}
