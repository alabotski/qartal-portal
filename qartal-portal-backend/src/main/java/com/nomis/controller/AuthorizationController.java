package com.nomis.controller;

import static org.springframework.http.HttpStatus.OK;

import com.nomis.service.AuthorizationService;
import com.nomis.shared.request.LoginRequest;
import com.nomis.shared.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthorizationController.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
@RestController
@RequestMapping("authorization")
@Slf4j
public class AuthorizationController {

  @Autowired
  private AuthorizationService authorizationService;

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setAuthorization(authorizationService.login(loginRequest));
    return new ResponseEntity<>(loginResponse, OK);
  }
}
