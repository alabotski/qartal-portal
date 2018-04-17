package com.nomis.client.logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.RemoteLogHandlerBase;
import com.google.gwt.user.client.Window;
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

  private static final LoggerService loggerService = GWT.create(LoggerService.class);

  @Override
  public void publish(LogRecord logRecord) {
    LogGwtRequest logGwtRequest = new LogGwtRequest();
    logGwtRequest.setLevel(logRecord.getLevel()
        .getName());
    logGwtRequest.setMessage(logRecord.getMessage());
    logGwtRequest.setSourceClassName(logGwtRequest.getSourceClassName());
    logGwtRequest.setSourceMethodName(logGwtRequest.getSourceMethodName());
    logGwtRequest.setThrowable(logRecord.getThrown());

    loggerService.log(logGwtRequest, new MethodCallback<Void>() {
      @Override
      public void onFailure(Method method, Throwable exception) {
        Window.alert("err");
      }

      @Override
      public void onSuccess(Method method, Void response) {
        Window.alert("scu");
      }
    });
  }
}
