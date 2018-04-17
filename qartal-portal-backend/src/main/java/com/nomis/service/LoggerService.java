package com.nomis.service;

import com.nomis.shared.request.LogGwtRequest;

/**
 * LoggerService.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public interface LoggerService {

  void writeLog(LogGwtRequest logGwtRequest);
}
