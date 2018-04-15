package com.nomis.client.widget.serverinfo;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.nomis.shared.model.ServerInfo;
import gwt.material.design.client.data.ListDataSource;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * ServerInfoView.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class ServerInfoView extends ViewWithUiHandlers<ServerInfoUiHandlers> implements ServerInfoWidget.MyView {

  interface Binder extends UiBinder<MaterialPanel, ServerInfoView> {

  }

  //  @UiField
  //  MaterialDataTable<ServerInfo> serverInfo;
  //  private ListDataSource<ServerInfo> listDataSource;

  @Inject
  ServerInfoView(Binder binder) {
    initWidget(binder.createAndBindUi(this));

    //    listDataSource = new ListDataSource<>();
    //    serverInfo.setDataSource(listDataSource);
    //    serverInfo.setRenderer(new BaseRenderer<>());
  }

  @Override
  public void addColumnKey(String caption) {
    //    serverInfo.addColumn(new TextColumn<ServerInfo>() {
    //      @Override
    //      public String getValue(ServerInfo object) {
    //        return object.getKey();
    //      }
    //    }, caption);
  }

  @Override
  public void addColumnValue(String caption) {
    //    serverInfo.addColumn(new TextColumn<ServerInfo>() {
    //      @Override
    //      public String getValue(ServerInfo object) {
    //        return object.getValue();
    //      }
    //    }, caption);
  }

  @Override
  public void setTableTitle(String title) {
    //    serverInfo.getTableTitle()
    //        .setText(title);
  }

  @Override
  public ListDataSource<ServerInfo> getDataSource() {
    //    return listDataSource;
    return null;
  }

}