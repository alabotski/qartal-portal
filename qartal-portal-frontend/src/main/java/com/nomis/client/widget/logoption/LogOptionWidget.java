package com.nomis.client.widget.logoption;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nomis.client.event.ClearEvent;
import com.nomis.client.event.LogInfoEvent;
import com.nomis.client.event.LogOptionEvent;
import com.nomis.client.event.LogOptionHandler;
import com.nomis.client.event.MessageEvent;
import com.nomis.client.rest.ServerService;
import com.nomis.client.rest.codec.LogInfoRequestCodec;
import com.nomis.client.rest.codec.LogInfoResponseCodec;
import com.nomis.shared.model.LogLevel;
import com.nomis.shared.request.LogInfoRequest;
import com.nomis.shared.response.LogInfoResponse;
import com.nomis.shared.response.ServerLogOptionResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.realityforge.gwt.websockets.client.WebSocket;
import org.realityforge.gwt.websockets.client.WebSocketListenerAdapter;

/**
 * LogInfoWidget.
 *
 * @author Aliaksei Labotski.
 * @since 4/15/18.
 */
public class LogOptionWidget extends PresenterWidget<LogOptionWidget.MyView> implements LogOptionUiHandlers,
    LogOptionHandler {

  interface MyView extends View, HasUiHandlers<LogOptionUiHandlers> {

    void setLevel(List<String> levelList);

    void resetLogLevel();

    void setEnabled(boolean enabled);

    void setShowBtnText(String caption);
  }

  @Inject
  private LogOptionConstants logOptionConstants;

  @Inject
  private ServerService serverService;

  @Inject
  private LogInfoResponseCodec logInfoResponseCodec;

  @Inject
  private LogInfoRequestCodec logInfoRequestCodec;

  private boolean enabled;
  private String webSocketUrl;
  private WebSocket wsServerLog;

  @Inject
  LogOptionWidget(EventBus eventBus, LogOptionWidget.MyView view) {
    super(eventBus, view);
    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    List<String> levelList = Stream.of(LogLevel.values())
        .map(Enum::name)
        .collect(Collectors.toList());
    getView().setLevel(levelList);
    setEnabled(false);

    addRegisteredHandler(LogOptionEvent.TYPE, this);
    super.onBind();
  }

  @Override
  protected void onReset() {
    wsDisconnect();
    super.onReset();
  }

  @Override
  public void show() {
    wsDisconnect();
    setEnabled(!enabled);
    if (enabled) {
      wsServerLog = WebSocket.newWebSocketIfSupported();
      if (null != wsServerLog) {
        wsServerLog.setListener(new WebSocketListenerAdapter() {
          @Override
          public void onMessage(final WebSocket webSocket, final String data) {
            LogInfoResponse logInfoResponse = logInfoResponseCodec.decode(data);
            LogInfoEvent.fire(LogOptionWidget.this, formatMessage(logInfoResponse));
          }
        });
        wsServerLog.connect(webSocketUrl);
        //      changeLogLevel();
      }
    }
    ClearEvent.fire(this);
  }

  @Override
  public void aplay(String singleValue) {
    changeLogLevel(LogLevel.valueOf(singleValue));
    MessageEvent.fire(this, singleValue + " " + logOptionConstants.setLogLevel());
  }

  @Override
  public void onLogOption(LogOptionEvent event) {
    serverService.serverLogOption(new MethodCallback<ServerLogOptionResponse>() {
      @Override
      public void onFailure(Method method, Throwable exception) {
        MessageEvent.fire(LogOptionWidget.this, logOptionConstants.serverLogOptionError());
      }

      @Override
      public void onSuccess(Method method, ServerLogOptionResponse response) {
        wsDisconnect();
        webSocketUrl = response.getWebSocketUrl();
        getView().resetLogLevel();
        setEnabled(false);
        //        ClearEvent.fire(LogOptionWidget.this);
        MessageEvent.fire(LogOptionWidget.this, logOptionConstants.serverLogOptionSuccess());
      }
    });
  }

  private void wsDisconnect() {
    if (wsServerLog != null && wsServerLog.isConnected()) {
      wsServerLog.close();
    }
  }

  private String formatMessage(LogInfoResponse logInfoResponse) {
    StringBuilder sb = new StringBuilder("<p>").append(logInfoResponse.getCurrentTime())
        .append(" [")
        .append(logInfoResponse
            .getLogLevel()
            .name())
        .append("] (Session ID = ")
        .append(logInfoResponse.getSessionId())
        .append(") - ")
        .append(logInfoResponse.getMessage())
        .append("</p>");
    return sb.toString();
  }

  private void changeLogLevel(LogLevel logLevel) {
    if (null != wsServerLog) {
      LogInfoRequest logInfoRequest = new LogInfoRequest();
      logInfoRequest.setLogLevel(logLevel);
      wsServerLog.send(logInfoRequestCodec.encode(logInfoRequest)
          .toString());
    }
  }

  private void setEnabled(boolean enabled) {
    this.enabled = enabled;
    getView().setEnabled(enabled);
    getView().setShowBtnText(enabled ? logOptionConstants.stop() : logOptionConstants.start());
  }
}
