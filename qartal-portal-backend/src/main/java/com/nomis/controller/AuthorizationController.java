package com.nomis.controller;

import static org.springframework.http.HttpStatus.OK;

import com.google.inject.Inject;
import com.nomis.request.LoginRequest;
import com.nomis.response.LoginResponse;
import com.nomis.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthorizationController.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
@RestController
@RequestMapping("/authorization/")
@Slf4j
public class AuthorizationController {

  @Inject
  private AuthorizationService authorizationService;

  @RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) {
    LoginResponse loginResponse = new LoginResponse();
    if (authorizationService== null){
      log.info("IS NULL");
    }
    loginResponse.setAuthorization(authorizationService.login(loginRequest));
    return new ResponseEntity<>(loginResponse, OK);
  }
}
