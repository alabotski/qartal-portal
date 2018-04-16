package com.nomis.application.rabbit.service;


import com.nomis.application.rabbit.converter.ActionType;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Sokolov
 */
@Service
public class AmqpPropertyService {

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

    @Value("${com.nomis.serviceType}")
    @NotNull
    private ActionType serviceType;


    @Value("${spring.rabbitmq.routingKey}")
    @NotNull
    private String routingKey;

    @Value("${spring.rabbitmq.statusQueue}")
    @NotNull
    private String defaultStatusQueue;

    @Value("${spring.rabbitmq.logQueue}")
    @NotNull
    private String defaultLogQueue;

    @Value("${spring.rabbitmq.optimizationResponseQueue}")
    @NotNull
    private String optimizationResponseQueue;

    @Value("${spring.rabbitmq.simulationResponseQueue}")
    @NotNull
    private String simulationResponseQueue;

    @Value("${spring.rabbitmq.baselineResponseQueue}")
    @NotNull
    private String baselineResponseQueue;

    @Value("${spring.rabbitmq.configQueue}")
    @NotNull
    private String defaultConfigQueue;

    public String getOptimizationResponseQueue() {
        return optimizationResponseQueue;
    }

    public String getSimulationResponseQueue() {
        return simulationResponseQueue;
    }

    public String getBaselineResponseQueue() {
        return baselineResponseQueue;
    }

    public String getAmqpHost() {
        return amqpHost;
    }

    public String getTopicExchangeName() {
        return topicExchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public String getAmqpUser() {
        return amqpUser;
    }

    public String getDefaultStatusQueue() {
        return defaultStatusQueue;
    }

    public String getDefaultConfigQueue() {
        return defaultConfigQueue;
    }

    public String getAmqpPassword() {
        return amqpPassword;
    }

    public int getAmqpPort() {
        return amqpPort;
    }

    public ActionType getServiceType() {
        return serviceType;
    }

    public String getDefaultLogQueue() {
        return defaultLogQueue;
    }

    public String getResponseQueue(ActionType actionType) {
        if (serviceType.equals(ActionType.NOMISSERVICES)) {

            return getResponseQueueForCombinedService(actionType);

        } else if (serviceType.equals(ActionType.OPTIMIZATION)
                || serviceType.equals(ActionType.SIMULATION)
                || serviceType.equals(ActionType.BASELINE)) {

            return getResponseQueueForSingleService(actionType);
        } else if (serviceType.equals(ActionType.UNDEFINED)) {

                    return getDefaultStatusQueue();

        } else {
            throw new RuntimeException(
                    "Unknown service type in application.yml file - " + serviceType);
        }
    }

    /**
     * This method is used to get response queue name when application is running as
     * single service (can execute OPTIMIZATION or SIMULATION, but not both)
     */
    private String getResponseQueueForSingleService(ActionType actionType) {

        if (serviceType.equals(ActionType.OPTIMIZATION) && serviceType.equals(actionType)) {
            return getOptimizationResponseQueue();

        } else if (serviceType.equals(ActionType.SIMULATION) && serviceType.equals(actionType)) {

            return getSimulationResponseQueue();

        } else if (serviceType.equals(ActionType.BASELINE) && serviceType.equals(actionType)) {

            return "Baseline";

        } else {

            throw new RuntimeException(
                    "Mismatch for service and action types. Service of type " + serviceType + "cant perform action of type " + actionType);

        }
    }

    /**
     * This method is used to get response queue name when application is running as
     * combined service (can execute OPTIMIZATION and SIMULATION)
     */
    private String getResponseQueueForCombinedService(ActionType actionType) {
        switch (actionType) {
            case SIMULATION:
                return getSimulationResponseQueue();

            case OPTIMIZATION:
                return getOptimizationResponseQueue();

            case BASELINE:
                return getBaselineResponseQueue();

            case STATUS:
                return getDefaultStatusQueue();

            case CANCEL:
                return getDefaultStatusQueue();

            case CONFIG:
                return getDefaultConfigQueue();

            case UNDEFINED:
                return getDefaultConfigQueue();

            default:
                throw new RuntimeException("Failed to get response queue name");
        }
    }
}
