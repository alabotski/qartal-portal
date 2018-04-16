package com.nomis.application.rabbit.comunication;

import com.rabbitmq.http.client.Client;
import com.rabbitmq.http.client.domain.QueueInfo;
import com.nomis.application.rabbit.service.AmqpPropertyService;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;


@Log4j2
@Component
public class RabbitHttppConnector {

    @Inject
    private AmqpPropertyService propertyService;

    public void itit() throws IOException, URISyntaxException {
        Client client = new Client(getURI(),
                propertyService.getAmqpUser(), propertyService.getAmqpPassword());

        log.info("ClusterName:" + client.getOverview().getClusterName());
        for (QueueInfo i :
                client.getQueues()) {
            log.info(" queue with name: " + i.getName());
        }
    }

    private String  getURI(){
        return        "http://"+propertyService.getAmqpHost()+":15672/api/";
    }

}
