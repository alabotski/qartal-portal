package com.nomis.service;

import com.nomis.shared.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * AuthorizationServiceImpl.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

  private static final String ADMIN = "Admin123";

  @Override
  public boolean login(LoginRequest loginRequest) {
    return loginRequest.getLogin()
        .equals(ADMIN) && loginRequest.getPassword()
        .equals(ADMIN);
  }
}
