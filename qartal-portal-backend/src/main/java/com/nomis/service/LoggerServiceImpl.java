package com.nomis.service;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.nomis.shared.request.LogGwtRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.event.Level;
import org.springframework.stereotype.Service;

/**
 * LoggerServiceImpl.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@Service
@Slf4j
public class LoggerServiceImpl implements LoggerService {

  private static final Map<String, Level> LEVELS;

  static {
    Map<String, Level> levels = new HashMap<>();
    levels.put("ALL", Level.TRACE);
    levels.put("CONFIG", Level.INFO);
    levels.put("FINE", Level.DEBUG);
    levels.put("FINER", Level.TRACE);
    levels.put("FINEST", Level.TRACE);
    levels.put("INFO", Level.INFO);
    levels.put("OFF", Level.WARN);
    levels.put("SEVERE", Level.ERROR);
    levels.put("WARNING", Level.WARN);
    LEVELS = Collections.unmodifiableMap(levels);
  }

  @Override
  public void writeLog(LogGwtRequest logGwtRequest) {
    if (isNotBlank(logGwtRequest.getLevel())) {
      Level level = LEVELS.get(logGwtRequest.getLevel());
      String message = isNotBlank((logGwtRequest.getMessage())) ? logGwtRequest.getMessage() : StringUtils.EMPTY;
      String sourceClassName = isNotBlank((logGwtRequest.getSourceClassName())) ? logGwtRequest.getSourceClassName() :
          StringUtils.EMPTY;
      String sourceMethodName =
          isNotBlank((logGwtRequest.getSourceMethodName())) ? logGwtRequest.getSourceMethodName() :
              StringUtils.EMPTY;

      String logmsg = sourceClassName + " " + sourceMethodName + " " + message;
      switch (level) {
        case INFO:
          log.info(logmsg, logGwtRequest.getThrowable());
          break;
        case WARN:
          log.warn(logmsg, logGwtRequest.getThrowable());
          break;
        case DEBUG:
          log.debug(logmsg, logGwtRequest.getThrowable());
          break;
        case ERROR:
          log.error(logmsg, logGwtRequest.getThrowable());
          break;
        case TRACE:
          log.trace(logmsg, logGwtRequest.getThrowable());
          break;
        default:
          break;
      }
    }
  }
}
