package com.nomis.rabbit.comunication;

import lombok.Data;

/**
 * ServiceStatusInfo.
 *
 * @author Alexander Sokolov.
 */
@Data
public class ServiceStatusInfo {

  private ServiceStatus status;

  private Long documentId;

  private String documentName;

  private String tenantId;

}
