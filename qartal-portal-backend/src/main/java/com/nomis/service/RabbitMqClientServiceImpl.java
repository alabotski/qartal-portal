package com.nomis.service;

import com.nomis.dto.NodeDto;
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

  @Inject
  private NodesService nodesService;

  private NodeDto rabbitMq;

  @PostConstruct
  public void init() {
    rabbitMq = nodesService.getNodeByNodeType("RabbitMQ");
  }
}
