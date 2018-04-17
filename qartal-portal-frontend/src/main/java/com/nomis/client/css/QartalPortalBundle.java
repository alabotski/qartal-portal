package com.nomis.client.css;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.DataResource.MimeType;
import com.google.gwt.resources.client.ImageResource;

public interface QartalPortalBundle extends ClientBundle {

  String MIME_TYPE = "image/svg+xml";

  @Source("404.jpg")
  ImageResource error();

  @Source("running.svg")
  @MimeType(MIME_TYPE)
  DataResource running();

  @Source("enable.svg")
  @MimeType(MIME_TYPE)
  DataResource enable();

  @Source("disabled.svg")
  @MimeType(MIME_TYPE)
  DataResource disabled();

  @Source("notactual.svg")
  @MimeType(MIME_TYPE)
  DataResource notActual();

  @Source("QartalPortal.gss")
  Style style();

  interface Style extends CssResource {

    String webPortal();
  }
}