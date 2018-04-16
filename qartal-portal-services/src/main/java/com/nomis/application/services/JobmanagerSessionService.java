package com.nomis.application.services;

import java.io.IOException;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
public interface JobmanagerSessionService {

    void removeSession(WebSocketSession session);

    void addClientSession(WebSocketSession session);

    void newMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException;
}