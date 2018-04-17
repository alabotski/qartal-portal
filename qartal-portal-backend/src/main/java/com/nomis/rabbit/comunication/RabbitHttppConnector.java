package com.nomis.rabbit.comunication;

import com.nomis.service.PropertyService;
import com.rabbitmq.http.client.Client;
import com.rabbitmq.http.client.domain.QueueInfo;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class RabbitHttppConnector {

  @Inject
  private PropertyService propertyService;

  public void itit() throws IOException, URISyntaxException {
    Client client = new Client(getUri(), propertyService.getAmqpUser(), propertyService.getAmqpPassword());
    log.info("ClusterName:" + client.getOverview()
        .getClusterName());
    for (QueueInfo i : client.getQueues()) {
      log.info(" queue with name: " + i.getName());
    }
  }

  private String getUri() {
    return "http://" + propertyService.getAmqpHost() + ":15672/api/";
  }

}
