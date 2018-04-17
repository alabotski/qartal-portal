package com.nomis.service;

import com.nomis.dto.NodeDto;
import java.util.Objects;

/**
 * @author Artur Kushner
 * @since 4/17/18
 */
public interface NodesService {

  void addNode(NodeDto node);

  void removeNodeById(long id);

  NodeDto getNodeById(long id);

}
