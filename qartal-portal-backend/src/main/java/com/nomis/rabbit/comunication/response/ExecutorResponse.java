package com.nomis.rabbit.comunication.response;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * @author Sokolov
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ExecutorResponse extends ServiceQueueResponse {


  private String tenantId;

  private String actionType;

  private String message;

  private String processingResult;

  private Integer asynchStatus;

  private Integer solutionStatus;

  private String documentId;

  public ExecutorResponse() {
  }

  public ExecutorResponse(String tenantId, String actionType, String message, String processingResult,
      Integer asynchStatus, Integer solutionStatus, String documentId) {

    this.tenantId = tenantId;
    this.actionType = actionType;
    this.message = message;
    this.processingResult = processingResult;
    this.asynchStatus = asynchStatus;
    this.solutionStatus = solutionStatus;
    this.documentId = documentId;
  }


  public String getProcessingResult() {
    return processingResult;
  }

  public void setProcessingResult(String processingResult) {
    this.processingResult = processingResult;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getAsynchStatus() {
    return asynchStatus;
  }

  public void setAsynchStatus(int asynchStatus) {
    this.asynchStatus = asynchStatus;
  }

  public Integer getSolutionStatus() {
    return solutionStatus;
  }

  public void setSolutionStatus(Integer solutionStatus) {
    this.solutionStatus = solutionStatus;
  }

  public String getDocumentId() {
    return documentId;
  }

  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

  public String getActionType() {
    return actionType;
  }

  public void setActionType(String actionType) {
    this.actionType = actionType;
  }


  public void setAsynchStatus(Integer asynchStatus) {
    this.asynchStatus = asynchStatus;
  }

  @Override
  public String toString() {
    return message + ". actionType " + actionType + ", tenantId " + tenantId
        + ", documentId " + documentId + ", processingResult " + processingResult;
  }


}
