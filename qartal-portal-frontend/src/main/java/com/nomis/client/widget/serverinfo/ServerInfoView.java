package com.nomis.client.widget.serverinfo;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.nomis.shared.model.ServerInfo;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.table.MaterialDataTable;
import gwt.material.design.client.ui.table.cell.TextColumn;

/**
 * ServerInfoView.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class ServerInfoView extends ViewWithUiHandlers<ServerInfoUiHandlers> implements ServerInfoWidget.MyView {

  interface Binder extends UiBinder<MaterialPanel, ServerInfoView> {

  }

  @UiField
  MaterialDataTable<ServerInfo> serverInfo;

  @Inject
  ServerInfoView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
    serverInfo.setVisibleRange(0, 100);
    serverInfo.getElement()
        .getChild(0)
        .removeFromParent();
    //    serverInfo.setRenderer(new BaseRenderer<>());
    //    serverInfo.getTableTitle().setVisible(false);
    //    serverInfo.getStretchIcon().setVisible(false);
    //    serverInfo.getMenu().setVisible(false);
    //    serverInfo.getColumnMenuIcon().setVisible(false);
    //    serverInfo.getTableIcon().setVisible(false);
  }

  @Override
  public void addColumnKey(String caption) {
    serverInfo.addColumn(new TextColumn<ServerInfo>() {
      @Override
      public String getValue(ServerInfo object) {
        return object.getKey();
      }
    }, caption);
  }

  @Override
  public void addColumnValue(String caption) {
    serverInfo.addColumn(new TextColumn<ServerInfo>() {
      @Override
      public String getValue(ServerInfo object) {
        return object.getValue();
      }
    }, caption);
  }

  @Override
  public void setTableTitle(String title) {
    //    serverInfo.getTableTitle()
    //        .setText(title);
  }

  @Override
  public MaterialDataTable<ServerInfo> getServerInfo() {
    return serverInfo;
  }

}