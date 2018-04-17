package com.nomis.service;

import com.nomis.dto.NodeDto;
import com.nomis.rabbit.comunication.RabbitMqHttpServiceImpl;
import com.nomis.shared.model.NodeName;
import com.nomis.shared.model.ServerStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * RabbitMqClientServiceImpl.
 *
 * @author Eugene Tsydzik.
 * @since 4/17/18.
 */
@Slf4j
@Service
public class RabbitMqClientServiceImpl implements RabbitMqClientService {

  private RabbitMqHttpServiceImpl rabbitMqHttpService;
  @Inject
  private NodesService nodesService;

  private NodeDto rabbitMq;

  @PostConstruct
  public void init() {
    rabbitMq = nodesService.getNodeByNodeType(NodeName.RabbitMQ.toString());
    String uri = getRabbitUrl(rabbitMq);
    rabbitMqHttpService = new RabbitMqHttpServiceImpl();
    rabbitMqHttpService.init(uri, (String) rabbitMq.getNodeProperties()
        .get("user"), (String) rabbitMq.getNodeProperties()
        .get("password"));

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    executorService.scheduleAtFixedRate((this::discoverServices), 0, 2, TimeUnit.SECONDS);

  }

  private String getRabbitUrl(NodeDto rabbitMq) {
    return "http://" + rabbitMq.getIpAddress() + ":15672/api/";
  }

  private void discoverServices() {

    List<NodeDto> servicesList = nodesService.getNodeListByNodeType("SERVICES");
    //servicesList.forEach(service -> nodesService.removeNodeById(service.getId()));

    if (rabbitMqHttpService.isRabbitHealthy()) {
      rabbitMq.setStatus(ServerStatus.ENABLE);
      List<NodeDto> servicesNodes = new ArrayList<>();
      Set<String> connectedHosts = rabbitMqHttpService.getConnections();

      connectedHosts.stream()
          .filter(Objects::nonNull)
          .forEach(host -> {
            NodeDto nodeDto = new NodeDto();
            nodeDto.setIpAddress(host);
            nodeDto.setNodeType("SERVICES");
            nodeDto.setStatus(ServerStatus.ENABLE);
            servicesNodes.add(nodeDto);
          });

      servicesNodes.forEach(service -> {
        NodeDto nodeDto = servicesList.stream()
            .filter(node -> node.getIpAddress()
                .equalsIgnoreCase(service.getIpAddress()))
            .findFirst()
            .orElse(null);
        if (Objects.isNull(nodeDto)) {
          nodesService.addNode(service);
        }
      });

      //      servicesList.stream()
      //          .filter(node -> servicesNodes.stream()
      //              .noneMatch(service -> service.getIpAddress()
      //                  .equalsIgnoreCase(node.getIpAddress())))
      //          .forEach(node -> node.setStatus(ServerStatus.NOT_ACTUAL));
    } else {
      rabbitMq.setStatus(ServerStatus.DISABLED);
    }


  }

}
