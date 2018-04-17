package com.nomis.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomis.dto.NodeDto;
import com.nomis.util.ResourcesUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

/**
 * @author Artur Kushner
 * @since 4/17/18
 */
@Service
@Slf4j
public class NodesServiceImpl implements NodesService {

  @Inject
  private ObjectMapper objectMapper;
  private Map<Long, NodeDto> nodes;


  @PostConstruct
  @SuppressWarnings("unchecked")
  public void init() throws IOException {
    nodes = new HashMap<>();

    List<NodeDto> nodeList = objectMapper.readValue(ResourcesUtil.getInstance()
        .getResource("nodes.json"), new TypeReference<List<NodeDto>>() {
    });

    nodeList.stream()
        .filter(Objects::nonNull)
        .forEach(this::addNode);
    log.info("NodeList initialized successfully.");

  }

  public long addNode(NodeDto node) {
    long id = nodes.keySet().stream()
        .mapToLong(Long::longValue)
        .max().orElse(0) + 1;
    node.setId(id);
    nodes.put(id, node);
    return id;
  }

  public void removeNodeById(long id) {
    nodes.remove(id);
  }

  public NodeDto getNodeById(long id) {
    return nodes.get(id);
  }

  public NodeDto getNodeByNodeType(String nodeType) {
    if(Strings.isNotEmpty(nodeType)) {
      return null;
    }
    return nodes.values().stream()
        .filter(Objects::nonNull)
        .filter(node -> nodeType.equalsIgnoreCase(node.getNodeType()))
      .findFirst().orElse(null);
  }
}
