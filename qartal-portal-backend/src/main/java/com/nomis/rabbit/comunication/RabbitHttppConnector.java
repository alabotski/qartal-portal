package com.nomis.rabbit.comunication;

import com.nomis.service.PropertyService;
import com.rabbitmq.http.client.Client;
import com.rabbitmq.http.client.domain.ConnectionInfo;
import com.rabbitmq.http.client.domain.QueueInfo;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class RabbitHttppConnector {

  @Inject
  PropertyService propertyService;

  String user = "nomisAdmin";

  //String ip =propertyService.getAmqpHost();

  String status = "DISCONNECT";


  private Client client;


  public synchronized Client getClient() {
    if (client == null) {
      try {
        client = new Client(getURI(),
            propertyService.getAmqpUser(), propertyService.getAmqpPassword());
        log.info("ClusterName:" + client.getOverview()
            .getClusterName());
        status = "CONNECTED";
        for (QueueInfo i :
            client.getQueues()) {
          log.info(" queue with name: " + i.getName());
        }

        while (true) {
          for (Map.Entry<Integer, String> entry : client.getConnections()
              .stream()
              .collect(Collectors.toMap(ConnectionInfo::getPeerPort, ConnectionInfo::getPeerHost))
              .entrySet()) {
            boolean reachable = false;
            try {
              reachable = hasService(InetAddress.getByName(entry.getValue()), entry.getKey());
            } catch (IOException e) {
              e.printStackTrace();
            }
            log.info(entry.getValue() + " is reachable: " + reachable);
          }
        }
      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    }
    return client;
  }

  public List<String> getConnections() {
    log.info("getConnections:");
    for (ConnectionInfo i : getClient().getConnections()) {
      log.info(" connection with name: " + i.toString());
    }
    return getClient().getConnections()
        .stream()
        .map(c -> c.getHost())
        .collect(Collectors.toList());
  }

  private String getURI() {
    return "http://" + propertyService.getAmqpHost() + ":15672/api/";
  }

  private boolean hasService(InetAddress host, int port) throws IOException {
    boolean status = false;
    Socket socket = new Socket();

    try {
      socket.connect(new InetSocketAddress(host, port), 5000);
      if (socket.isConnected()) {
        socket.close();
        status = true;
      }

    } catch (ConnectException | NoRouteToHostException | SocketTimeoutException ex) {
      log.info(ex.getMessage());
    }

    return status;
  }
}
