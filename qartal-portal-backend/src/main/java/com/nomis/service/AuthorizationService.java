package com.nomis.service;

import com.nomis.shared.request.LoginRequest;

/**
 * AuthorizationService.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
public interface AuthorizationService {

  boolean login(LoginRequest loginRequest);
}
