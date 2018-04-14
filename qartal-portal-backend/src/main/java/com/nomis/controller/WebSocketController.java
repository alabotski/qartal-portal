package com.nomis.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * WebSocketController.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@Controller
public class WebSocketController {

  private final SimpMessagingTemplate template;

  @Autowired
  WebSocketController(SimpMessagingTemplate template) {
    this.template = template;
  }

  @MessageMapping("/send/message")
  public void onReceivedMesage(String message) {
    this.template.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + message);
  }

  @Scheduled(fixedRate = 2000)
  public void reportCurrentTime() {
    this.template.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + "Hello");
  }
}
