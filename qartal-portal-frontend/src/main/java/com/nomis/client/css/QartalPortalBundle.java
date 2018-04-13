package com.nomis.client.css;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface QartalPortalBundle extends ClientBundle {

  @Source("404.jpg")
  ImageResource error();

  @Source("QartalPortal.gss")
  Style style();

  interface Style extends CssResource {

    String webPortal();
  }
}