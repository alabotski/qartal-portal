package com.nomis.rabbit.comunication.response;

import com.nomis.rabbit.comunication.ServiceStatus;
import com.nomis.rabbit.comunication.ServiceStatusInfo;

/**
 * StatusResponse.
 *
 * @author Alexander Sokolov.
 */
public class StatusResponse extends ServiceQueueResponse {

  private ServiceStatusInfo serviceStatus;
  private String message;

  public ServiceStatusInfo getServiceStatus() {
    return serviceStatus;
  }

  public void setServiceStatus(ServiceStatusInfo serviceStatus) {
    this.serviceStatus = serviceStatus;
  }

  public void setServiceStatus(ServiceStatus serviceStatus) {
    ServiceStatusInfo serviceStatusInfo = new ServiceStatusInfo();
    serviceStatusInfo.setStatus(serviceStatus);
    this.serviceStatus = serviceStatusInfo;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "StatusResponse{ serviceStatus=" + serviceStatus.getStatus() + "documentId="
        + (serviceStatus.getDocumentId() != null ? serviceStatus.getDocumentId() : "null")
        + "tenantId=" + (serviceStatus.getTenantId() != null ? serviceStatus.getTenantId() : "null")
        + "documentName=" + (serviceStatus.getDocumentName() != null ? serviceStatus.getDocumentName() : "null")
        + ", message='" + message + "'\' IP='" + super.getIpAddress() + " '\'}";
  }
}
