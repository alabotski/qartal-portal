package com.nomis.application.rabbit.exception;


import com.nomis.application.rabbit.comunication.ServiceStatus;
import com.nomis.application.rabbit.comunication.response.StatusResponse;
import com.nomis.application.rabbit.converter.ActionType;
import com.nomis.application.rabbit.service.AmqpPropertyService;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Sokolov
 */
@Component
public class CustomExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {

    private static final Logger log = LogManager.getLogger(CustomExceptionStrategy.class);

    @Inject
    AmqpPropertyService propertyService;

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Inject
    private AmqpPropertyService amqpPropertyServicel;


    /**
     * This method is used to prevent broker from requeueing message
     * in case of exception during message conversion.
     * Because we use exclusive queues such behaviour may lead to infinite cycle
     * of redispatching message which throws exception to the same queue.
     *
     * @param cause {@link Throwable} object which was thrown during message convertation to Java
     *              type
     * @return always return 'false'.
     */
    @Override
    protected boolean isUserCauseFatal(Throwable cause) {
        return true;
    }


    /**
     * Marks all exceptions which can be thrown during request processing as fatal which prevents
     * messages from requeueing
     */
    @Override
    public boolean isFatal(Throwable t) {
        if (t instanceof ListenerExecutionFailedException) {
            ListenerExecutionFailedException listenerException = (ListenerExecutionFailedException) t;
            String errorMsg = "Failed to process inbound message from queue "
                    + listenerException.getFailedMessage().getMessageProperties().getConsumerQueue()
                    + "; failed message: " + listenerException.getFailedMessage();

            log.error(errorMsg, t);
            ActionType actionType =ActionType.getFromRoutingKey(
                    listenerException.getFailedMessage()
                            .getMessageProperties()
                            .getReceivedRoutingKey());
            sendInputError(actionType, errorMsg);
        }
        return true;
    }

    /**
     * Sends responce to RabbitMQ with 'error' status if {@link org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer}

     * @param actionType type of incoming request
     * @param message error message for response
     */
    public void sendInputError(ActionType actionType, String message) {

        StatusResponse inputError = new StatusResponse();
        inputError.setServiceStatus(ServiceStatus.ERROR);
        inputError.setMessage(message);

        String queue = propertyService.getResponseQueue(actionType);

        rabbitTemplate.convertAndSend(amqpPropertyServicel.getTopicExchangeName(),
                queue, inputError);

    }
}
