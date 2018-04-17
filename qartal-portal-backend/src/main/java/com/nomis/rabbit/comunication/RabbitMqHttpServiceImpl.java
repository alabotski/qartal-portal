package com.nomis.rabbit.comunication;

import com.rabbitmq.http.client.Client;
import com.rabbitmq.http.client.domain.ConnectionInfo;
import com.rabbitmq.http.client.domain.QueueInfo;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by Alexander Sokolov
 * on 4/17/18.
 */

@Slf4j
public class RabbitMqHttpServiceImpl {


  private Client client;


  public void init(String url, String user, String password) {

    try {
      client = new Client(url,
          user, password);
      log.info("ClusterName:" + client.getOverview()
          .getClusterName());
    } catch (Exception ex) {
      log.error("Failed to connect to RabbitMq", ex);
    }
  }

  public boolean isRabbitHealthy() {
    try {
      client.whoAmI();
      return true;
    }
    catch (Exception ex) {
      log.error("RabbitMq is not alive: ", ex);
      return false;
    }
  }

  public Set<String> getConnections() {
    return client.getConnections()
        .stream()
        .map(ConnectionInfo::getHost)
        .collect(Collectors.toSet());
  }

}
