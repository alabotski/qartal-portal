package com.nomis.application.handler;

import com.nomis.application.services.JobmanagerSessionService;
import java.io.IOException;
import javax.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
@Component
@Log4j2
public class JobmanagerWebSocketHandler extends AbstractWebSocketHandler implements WebSocketHandler {

    @Inject
    private JobmanagerSessionService jobmanagerSessionService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        jobmanagerSessionService.addClientSession(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        try {
            jobmanagerSessionService.newMessage(session, message);
        } catch (IOException e) {
            log.error("Invalid message - ", e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Websocket connection closed......" + session.getId() + " Close status ... " + closeStatus.getCode());
        jobmanagerSessionService.removeSession(session);
    }
}
