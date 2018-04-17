package com.nomis.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.nomis.service.LoggerService;
import com.nomis.shared.request.LogGwtRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoggerController.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@RestController
@RequestMapping("logger")
@Slf4j
public class LoggerController {

  @Autowired
  private LoggerService loggerService;

  @PostMapping(value = "/log", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> login(@RequestBody @Valid LogGwtRequest logGwtRequest) {
    loggerService.writeLog(logGwtRequest);
    return new ResponseEntity<>(NO_CONTENT);
  }
}
