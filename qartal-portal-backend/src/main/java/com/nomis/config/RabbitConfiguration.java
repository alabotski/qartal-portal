package com.nomis.config;

import com.nomis.rabbit.converter.ActionType;
import com.nomis.rabbit.exception.CustomExceptionStrategy;
import com.nomis.rabbit.service.AMQPRoutingKeysService;
import com.nomis.rabbit.service.AmqpPropertyService;
import com.nomis.rabbit.status.TaskStatusManager;
import com.nomis.rabbit.status.TaskStatusManagerImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


/**
 * @author Sokolov
 */
@Configuration
@EnableRabbit
@ComponentScan(
    value = {"com.nomis"})
public class RabbitConfiguration {


  @Inject
  private AmqpPropertyService propertyService;

  @Inject
  private AMQPRoutingKeysService routingKeysService;

  @Inject
  CustomExceptionStrategy customExceptionStrategy;

  private Map<ActionType, Queue> queuesMap;


  @Bean
  public ConnectionFactory rabbitConnectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
        propertyService.getAmqpHost());
    connectionFactory.setCacheMode(
        CachingConnectionFactory.CacheMode.CHANNEL); // Mandatory option. Only this cash mode permit auto-declaring of queues in rabbitMq broker.
    connectionFactory.setPort(propertyService.getAmqpPort());
    connectionFactory.setUsername(propertyService.getAmqpUser());
    connectionFactory.setPassword(propertyService.getAmqpPassword());

    return connectionFactory;
  }


  @Bean
  public AmqpAdmin rabbitAdmin(ConnectionFactory factory) {
    AmqpAdmin admin = new RabbitAdmin(factory);
    admin.declareExchange(exchange());
    return admin;
  }


  @Bean
  public List<Queue> queues() {
    return getQueuesByServiceType();

  }


  @Bean
  Exchange exchange() {
    TopicExchange exchange = new TopicExchange(propertyService.getTopicExchangeName(), true,
        false);
    exchange.setShouldDeclare(true);
    return exchange;
  }


  @Bean
  @DependsOn("queues")
  public List<Binding> binding() {
    return getBindingByServiceType();
  }


  /**
   * Overrides  {@link ConditionalRejectingErrorHandler.DefaultExceptionStrategy}
   * method and process all exception as fatal, which helps to prevent requeueing messages with
   * exceptions.
   */
  @Bean
  public ConditionalRejectingErrorHandler conditionalRejectingErrorHandler() {
    ConditionalRejectingErrorHandler handler = new ConditionalRejectingErrorHandler(
        customExceptionStrategy);
    return handler;
  }


  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {

    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setConcurrentConsumers(1);
    factory.setMaxConcurrentConsumers(1);
    factory.setDefaultRequeueRejected(false);
    factory.setErrorHandler(conditionalRejectingErrorHandler());
    return factory;
  }


  @Bean
  public TaskStatusManager taskStatusManager() {
    return new TaskStatusManagerImpl();
  }

  /**
   * Dynamically creates binding according to service type. If service type is {@link
   * ActionType#NOMISSERVICES} then it creates bindings for optimization and simulation services,
   * if service type is {@link ActionType#OPTIMIZATION} or {@link ActionType#SIMULATION} it
   * creates binding only for appropriate service
   *
   * @return List of {@link Binding} for RabbitMq
   */
  private List<Binding> getBindingByServiceType() {
    ActionType serviceType = propertyService.getServiceType();

    List<Binding> bindingList = new ArrayList<>(3);

    //add binding for STATUS queue
    bindingList.add(BindingBuilder.bind(queuesMap.get(ActionType.STATUS))
        .to(exchange())
        .with(propertyService.getDefaultStatusQueue())
        .noargs());

    bindingList.add(BindingBuilder.bind(queuesMap.get(ActionType.LOG))
        .to(exchange())
        .with(propertyService.getDefaultLogQueue())
        .noargs());

    bindingList.add(BindingBuilder.bind(queuesMap.get(ActionType.CONFIG))
        .to(exchange())
        .with(propertyService.getDefaultConfigQueue())
        .noargs());

    if (serviceType.equals(ActionType.NOMISSERVICES)) {

      //add binding for OPTIMIZATION queue
      bindingList.add(BindingBuilder.bind(queuesMap.get(ActionType.OPTIMIZATION))
          .to(exchange())
          .with(propertyService.getOptimizationResponseQueue())
          .noargs());

      //add binding for SIMULATION queue
      bindingList.add(BindingBuilder.bind(queuesMap.get(ActionType.SIMULATION))
          .to(exchange())
          .with(propertyService.getSimulationResponseQueue())
          .noargs());

      //add binding for BASELINE queue
      bindingList.add(BindingBuilder.bind(queuesMap.get(ActionType.BASELINE))
          .to(exchange())
          .with(propertyService.getBaselineResponseQueue())
          .noargs());

    } else if (serviceType.equals(ActionType.OPTIMIZATION)) {

      //add binding for OPTIMIZATION queue
      bindingList.add(BindingBuilder.bind(queuesMap.get(ActionType.OPTIMIZATION))
          .to(exchange())
          .with(propertyService.getOptimizationResponseQueue())
          .noargs());

    } else if (serviceType.equals(ActionType.SIMULATION)) {

      //add binding for SIMULATION queue
      bindingList.add(BindingBuilder.bind(queuesMap.get(ActionType.SIMULATION))
          .to(exchange())
          .with(propertyService.getSimulationResponseQueue())
          .noargs());

    } else if (serviceType.equals(ActionType.BASELINE)) {

      //add binding for BASELINE queue
      bindingList.add(BindingBuilder.bind(queuesMap.get(ActionType.BASELINE))
          .to(exchange())
          .with(propertyService.getBaselineResponseQueue())
          .noargs());

    } else {

      throw new RuntimeException(
          "Unknown service type in application.yml file - " + serviceType);
    }

    return bindingList;
  }


  /**
   * Dynamically creates queues according to service type. If service type is {@link
   * ActionType#NOMISSERVICES} then it creates queues for optimization and simulation services,
   * if service type is {@link ActionType#OPTIMIZATION} or {@link ActionType#SIMULATION} it
   * creates queues only for appropriate service
   *
   * @return List of {@link Binding} for RabbitMq
   */
  public List<Queue> getQueuesByServiceType() {
    ActionType serviceType = propertyService.getServiceType();

    queuesMap = new HashMap<>(3);

    Queue statusQueue = new Queue(routingKeysService.defaultStatusQueue(), true, false, false);
    Queue logQueue = new Queue(propertyService.getDefaultLogQueue(), true, false, false);
    Queue optimizationQueue = new Queue(propertyService.getOptimizationResponseQueue(), true,
        false, false);
    Queue simulationQueue = new Queue(propertyService.getSimulationResponseQueue(), true, false,
        false);
    Queue configQueue = new Queue(propertyService.getDefaultConfigQueue(), true, false,
        false);
    Queue baselineQueue = new Queue(propertyService.getBaselineResponseQueue(), true, false,
        false);

    //add STATUS queue
    queuesMap.put(ActionType.STATUS, statusQueue);
    queuesMap.put(ActionType.LOG, logQueue);
    queuesMap.put(ActionType.CONFIG, configQueue);

    if (serviceType.equals(ActionType.NOMISSERVICES)) {
      //add OPTIMIZATION queue
      queuesMap.put(ActionType.OPTIMIZATION, optimizationQueue);

      //add SIMULATION queue
      queuesMap.put(ActionType.SIMULATION, simulationQueue);

      //add BASELINE queue
      queuesMap.put(ActionType.BASELINE, baselineQueue);

    } else if (serviceType.equals(ActionType.OPTIMIZATION)) {

      //add OPTIMIZATION queue
      queuesMap.put(ActionType.OPTIMIZATION, optimizationQueue);

    } else if (serviceType.equals(ActionType.SIMULATION)) {

      //add SIMULATION queue
      queuesMap.put(ActionType.SIMULATION, simulationQueue);

    } else if (serviceType.equals(ActionType.BASELINE)) {

      //add BASELINE queue
      queuesMap.put(ActionType.BASELINE, baselineQueue);

    } else {

      throw new RuntimeException(
          "Unknown service type in application.yml file - " + serviceType);
    }

    return queuesMap.entrySet()
        .stream()
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
  }
}
