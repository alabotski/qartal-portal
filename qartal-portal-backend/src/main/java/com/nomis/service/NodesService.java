package com.nomis.service;

import com.nomis.dto.NodeDto;
import java.util.List;
import java.util.Map;

/**
 * NodesService.
 *
 * @author Artur Kushner.
 * @since 4/17/18.
 */
public interface NodesService {

  long addNode(NodeDto node);

  void updateNode(long id, NodeDto nodeDto);

  void removeNodeById(long id);

  NodeDto getNodeById(long id);

  NodeDto getNodeByNodeType(String nodeType);

  List<NodeDto> getNodeListByNodeType(String nodeType);

  Map<Long, NodeDto> getAllNodes();
}
