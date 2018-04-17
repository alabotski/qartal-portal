package com.nomis.dto;

import java.util.List;
import lombok.Data;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
@Data
public class SimulationJobs {

  private List<Simulation> queued;
  private List<Simulation> running;
}