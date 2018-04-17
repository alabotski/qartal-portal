package com.nomis.rabbit.service;

import java.util.Objects;
import javax.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * AMQPRoutingKeysService.
 *
 * @author Alexander Sokolov.
 */
@Configuration
@ComponentScan({"com.nomis"})
public class AmqpRoutingKeysService {

  public static final String CONTROL_EXCHANGE_POSTFIX = ".control";

  @Inject
  private AmqpPropertyService propertyService;


  @Bean
  public String exchangeName() {
    return Objects.requireNonNull(propertyService.getTopicExchangeName(),
        "Exchange name isn't correctly specified in service's corresponding '.yml' file");
  }

  @Bean
  public String controlExchangeName() {
    return Objects.requireNonNull(propertyService.getTopicExchangeName() + CONTROL_EXCHANGE_POSTFIX,
        "Control Exchange name isn't correctly specified in service's corresponding '.yml' file");
  }


  @Bean
  public String defaultStatusQueue() {
    return propertyService.getDefaultStatusQueue();
  }


  @Bean
  public String routingKeyToListen() {
    return new StringBuilder(routingKey()).append(".*")
        .toString();
  }

  @Bean
  public String controlRoutingKeyToListen() {
    return new StringBuilder(routingKey()).append(".control")
        .append(".*")
        .toString();
  }

  private String routingKey() {
    return Objects.requireNonNull(propertyService.getRoutingKey(), "Routing key isn't correctly specified in "
        + "service's corresponding '.yml' file");
  }
}
