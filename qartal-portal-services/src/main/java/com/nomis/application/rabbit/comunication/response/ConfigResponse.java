package com.nomis.application.rabbit.comunication.response;

/**
 * @autor Sokolov
 */
@Deprecated
public class ConfigResponse extends ServiceQueueResponse {

    private String tenantId;

    private String result;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
