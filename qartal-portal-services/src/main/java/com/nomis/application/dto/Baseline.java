package com.nomis.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Baseline {
    private String tenantId;
    private String vertical;
    private String baselineName;
    private int baselineId;
    private int userId;
    private String userName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Baseline baseline = (Baseline) o;

        if (baselineId != baseline.baselineId) return false;
        if (userId != baseline.userId) return false;
        if (tenantId != null ? !tenantId.equals(baseline.tenantId) : baseline.tenantId != null) return false;
        if (vertical != null ? !vertical.equals(baseline.vertical) : baseline.vertical != null) return false;
        if (baselineName != null ? !baselineName.equals(baseline.baselineName) : baseline.baselineName != null)
            return false;
        return userName != null ? userName.equals(baseline.userName) : baseline.userName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tenantId != null ? tenantId.hashCode() : 0);
        result = 31 * result + (vertical != null ? vertical.hashCode() : 0);
        result = 31 * result + (baselineName != null ? baselineName.hashCode() : 0);
        result = 31 * result + baselineId;
        result = 31 * result + userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}
