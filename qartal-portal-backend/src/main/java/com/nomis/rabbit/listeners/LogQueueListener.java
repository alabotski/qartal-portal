package com.nomis.rabbit.listeners;

import com.nomis.rabbit.comunication.RabbitMqLogService;
import java.nio.charset.Charset;
import javax.inject.Inject;
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

  @Inject
  RabbitMqLogService rabbitMqLogService;

  @RabbitHandler
  public void onMessage(@Payload byte[] message) {
    rabbitMqLogService.pushLog(new String(message, Charset.forName("UTF-8")));
    log.info(new String(message, Charset.forName("UTF-8")));
  }


}
