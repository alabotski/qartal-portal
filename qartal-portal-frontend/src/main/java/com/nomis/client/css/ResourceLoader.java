package com.nomis.client.css;

import com.google.inject.Inject;

/**
 * ResourceLoader.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public class ResourceLoader {

  @Inject
  ResourceLoader(QartalPortalBundle qartalPortalBundle) {
    qartalPortalBundle.style()
        .ensureInjected();
  }
}
