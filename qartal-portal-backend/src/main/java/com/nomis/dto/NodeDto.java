package com.nomis.dto;

import com.nomis.shared.model.ServerStatus;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author Artur Kushner
 * @since 4/17/18
 */
@Data
public class NodeDto {

  private long id;
  private String nodeType;
  private String ipAddress;
  private ServerStatus status;
  private Map<String, Object> nodeProperties;

  public Map<String, Object> getNode() {
    Map<String, Object> properties = new HashMap<>(nodeProperties);
    properties.put("id", id);
    properties.put("type", nodeType);
    properties.put("status", status);
    return properties;
  }

}
