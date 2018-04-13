package com.nomis.client.gin;

import com.gwtplatform.mvp.client.PreBootstrapper;
import org.fusesource.restygwt.client.Defaults;

/**
 * InitClientModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class InitClientModule implements PreBootstrapper {

  private static final String rootURL = "/";

  @Override
  public void onPreBootstrap() {
    Defaults.setServiceRoot(rootURL);
  }
}
