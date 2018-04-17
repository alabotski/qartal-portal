package com.nomis.rabbit.listeners;


import com.nomis.rabbit.comunication.RabbitHttppConnector;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMqListener.
 *
 * @author Alexander Sokolov.
 */
@Slf4j
@Component
public class RabbitMqListener {

  private Map<String, String> mapOfMessages = new HashMap<>();

  @Inject
  private RabbitHttppConnector rabbitHttppConnector;

  @RabbitListener(queues = {"logmessages", "basResponseQueue", "simResponseQueue"})
  public void processQueue1(Message message) {
    mapOfMessages.put(getKey(message.getMessageProperties()
        .getReceivedRoutingKey()), decodeUtf8(message.getBody()));
    log.info("Received from queue: " + message.getMessageProperties()
        .toString() + "  with body" + decodeUtf8(message.getBody()));
    try {
      rabbitHttppConnector.itit();
    } catch (IOException e) {
      log.error("processQueue1", e);
    } catch (URISyntaxException e) {
      log.error("processQueue1", e);
    }
  }

  private String decodeUtf8(byte[] bytes) {
    return new String(bytes, StandardCharsets.UTF_8);
  }

  private String getKey(String receivedRoutingKey) {
    return receivedRoutingKey + UUID.randomUUID()
        .toString();
  }

}
