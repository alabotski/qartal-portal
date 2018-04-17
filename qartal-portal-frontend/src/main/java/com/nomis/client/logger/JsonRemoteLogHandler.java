package com.nomis.client.logger;

import com.google.gwt.logging.client.RemoteLogHandlerBase;
import com.google.inject.Inject;
import com.nomis.client.rest.LoggerService;
import com.nomis.shared.request.LogGwtRequest;
import java.util.logging.LogRecord;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * JsonRemoteLogHandler.
 *
 * @author Aliaksei Labotski.
 * @since 4/17/18.
 */
public class JsonRemoteLogHandler extends RemoteLogHandlerBase {

  @Inject
  private LoggerService loggerService;

  @Override
  public void publish(LogRecord logRecord) {
    LogGwtRequest logGwtRequest = new LogGwtRequest();
    logGwtRequest.setLevel(logRecord.getLevel()
        .getName());
    logGwtRequest.setMessage(logRecord.getMessage());
    logGwtRequest.setThrowable(logRecord.getThrown());

    loggerService.log(logGwtRequest, new MethodCallback<Void>() {
      @Override
      public void onFailure(Method method, Throwable exception) {

      }

      @Override
      public void onSuccess(Method method, Void response) {

      }
    });
  }
}
