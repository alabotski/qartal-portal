package com.nomis.service;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
@Service
@Data
public class PropertyService {

  @Value("${com.rednavis.socket.connectUrl}")
  private String connectUrl;

  @Value("${com.rednavis.socket.port}")
  private String jobManagerPort;

  @Value("${com.rednavis.socket.protocol}")
  private String jobManagerProtocol;

  @Value("${com.rednavis.socket.apiPath}")
  private String jobManagerApiPath;

  @Value("${spring.rabbitmq.amqpHost}")
  @NotNull
  private String amqpHost;

  @Value("${spring.rabbitmq.amqpUser}")
  @NotNull
  private String amqpUser;

  @Value("${spring.rabbitmq.amqpPassword}")
  @NotNull
  private String amqpPassword;

  @Value("${spring.rabbitmq.amqpPort}")
  private int amqpPort;


  @Value("${spring.rabbitmq.topic.exchange}")
  @NotNull
  private String topicExchangeName;

  @Value("${spring.rabbitmq.logQueue}")
  @NotNull
  private String logQueue;
}