package com.nomis.service;

import com.nomis.dto.NodeDto;
import com.nomis.shared.model.NodeName;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * NpoClientServiceImpl.
 *
 * @author Eugene Tsydzik.
 * @since 4/17/18.
 */
@Slf4j
@Service
public class NpoClientServiceImpl implements NpoClientService {

  @Inject
  private NodesService nodesService;

  private NodeDto npo;

  @PostConstruct
  public void init() {
    npo = nodesService.getNodeByNodeType(NodeName.NPO.toString());
  }
}
