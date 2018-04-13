package com.nomis.client.css;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface QartalPortalBundle extends ClientBundle {

  @Source("QartalPortal.gss")
  Style style();

  interface Style extends CssResource {

    String webPortal();
  }
}