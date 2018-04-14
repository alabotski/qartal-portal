package com.nomis.controller;

import static org.springframework.http.HttpStatus.OK;

import com.nomis.service.ServerService;
import com.nomis.shared.response.ServerInfoResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ServerController.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@RestController
@RequestMapping("server")
@Slf4j
public class ServerController {

  @Autowired
  private ServerService serverService;

  @GetMapping(value = "/serverInfo", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ServerInfoResponse> serverInfo() throws IOException {
    ServerInfoResponse serverInfoResponse = serverService.getServerInfo();
    return new ResponseEntity<>(serverInfoResponse, OK);
  }
}
