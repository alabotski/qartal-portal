package com.nomis.client.security;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;
import com.nomis.client.model.Person;

/**
 * LoggedInGatekeeper.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
@DefaultGatekeeper
public class LoggedInGatekeeper implements Gatekeeper {

  @Inject
  Person person;

  @Inject
  LoggedInGatekeeper() {
  }

  @Override
  public boolean canReveal() {
    return person.isAuthorization();
  }

}
