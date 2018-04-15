package com.nomis.client.rest.dispatcher;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.nomis.client.event.LoadingEvent;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.dispatcher.DefaultFilterawareDispatcher;

/**
 * RestDispatcher.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class RestDispatcher extends DefaultFilterawareDispatcher {

  @Inject
  private EventBus eventBus;

  @Override
  public Request send(Method method, RequestBuilder builder) throws RequestException {
    RequestCallback callback = builder.getCallback();
    builder.setCallback(new RequestCallback() {
      @Override
      public void onResponseReceived(Request req, Response res) {
        callback.onResponseReceived(req, res);
        eventBus.fireEvent(new LoadingEvent(false));
      }

      @Override
      public void onError(Request req, Throwable ex) {
        eventBus.fireEvent(new LoadingEvent(false));
      }

    });
    eventBus.fireEvent(new LoadingEvent(true));
    return super.send(method, builder);
  }

}
