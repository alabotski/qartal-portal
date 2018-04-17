package com.nomis.rabbit.listeners;


import com.nomis.rabbit.comunication.RabbitHttppConnector;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.UUID;
import javax.inject.Inject;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Sokolov
 */
@Slf4j
@EnableRabbit
@Component
public class RabbitMqListener {

  private HashMap<String, String> mapOfMessages = new HashMap<>();

  @Inject
  private RabbitHttppConnector rabbitHttppConnector;

  @RabbitListener(queues = {"logmessages", "basResponseQueue", "simResponseQueue"})
  public void processQueue1(Message message) {
    mapOfMessages.put(getKey(message.getMessageProperties()
        .getReceivedRoutingKey()), decodeUTF8(message.getBody()));
    log.info("Received from queue: " + message.getMessageProperties()
        .toString() + "  with body" + decodeUTF8
        (message.getBody()));
    try {
      rabbitHttppConnector.itit();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

  private String decodeUTF8(byte[] bytes) {
    return new String(bytes, UTF8_CHARSET);
  }

  private String getKey(String receivedRoutingKey) {
    return receivedRoutingKey + UUID.randomUUID()
        .toString();
  }

}
