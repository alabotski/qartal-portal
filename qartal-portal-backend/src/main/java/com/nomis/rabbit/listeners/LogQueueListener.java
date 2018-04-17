package com.nomis.rabbit.listeners;

import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RabbitListener(id = "config",
    bindings = @QueueBinding(value = @Queue(autoDelete = "false", durable = "true", value = "#{logQueue}"),
        key = "#{logQueue}",
        exchange = @Exchange(value = "#{exchangeName}", type = ExchangeTypes.TOPIC, durable = "true")))
@Component
@Slf4j
public class LogQueueListener {


  @RabbitHandler
  public void onMessage(@Payload byte[] message) {

    log.info(new String(message, Charset.forName("UTF-8")));

  }


}
