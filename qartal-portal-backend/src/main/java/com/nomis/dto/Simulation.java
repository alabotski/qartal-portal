package com.nomis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Simulation {

  private String tenantId;
  private String vertical;
  private String name;
  private int userId;
  private String userName;
  private int optScenarioId;
  private String state;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    Simulation that = (Simulation) o;

    if (userId != that.userId) {
      return false;
    }
    if (optScenarioId != that.optScenarioId) {
      return false;
    }
    if (tenantId != null ? !tenantId.equals(that.tenantId) : that.tenantId != null) {
      return false;
    }
    if (vertical != null ? !vertical.equals(that.vertical) : that.vertical != null) {
      return false;
    }
    if (name != null ? !name.equals(that.name) : that.name != null) {
      return false;
    }
    if (userName != null ? !userName.equals(that.userName) : that.userName != null) {
      return false;
    }
    return state != null ? state.equals(that.state) : that.state == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (tenantId != null ? tenantId.hashCode() : 0);
    result = 31 * result + (vertical != null ? vertical.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + userId;
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    result = 31 * result + optScenarioId;
    result = 31 * result + (state != null ? state.hashCode() : 0);
    return result;
  }
}