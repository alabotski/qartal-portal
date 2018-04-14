package com.nomis.client.css;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.DataResource.MimeType;
import com.google.gwt.resources.client.ImageResource;

public interface QartalPortalBundle extends ClientBundle {

  @Source("404.jpg")
  ImageResource error();

  @Source("running.svg")
  @MimeType("image/svg+xml")
  DataResource running();

  @Source("enable.svg")
  @MimeType("image/svg+xml")
  DataResource enable();

  @Source("disable.svg")
  @MimeType("image/svg+xml")
  DataResource disable();

  @Source("QartalPortal.gss")
  Style style();

  interface Style extends CssResource {

    String webPortal();
  }
}