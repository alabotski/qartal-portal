package com.nomis.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomis.dto.NodeDto;
import com.nomis.shared.response.ServerStatusResponse;
import com.nomis.util.ResourcesUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.soap.Node;
import lombok.extern.slf4j.Slf4j;
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
  public void init() throws IOException{
    nodes = new HashMap<>();

    List<NodeDto> nodeList = objectMapper.readValue(ResourcesUtil.getInstance()
        .getResource("nodes.json"), new TypeReference<List<NodeDto>>(){});


    nodeList.stream()
        .filter(Objects::nonNull)
        .forEach(this::addNode);
    log.info("NodeList initialized successfully.");

  }

  public void addNode(NodeDto node) {
    long id = nodes.keySet().stream()
        .mapToLong(Long::longValue)
        .max().orElse(0) + 1;
    node.setId(id);
    nodes.put(id, node);
  }

  public void removeNodeById(long id) {
    nodes.remove(id);
  }

  public NodeDto getNodeById(long id) {
    return nodes.get(id);
  }
}
