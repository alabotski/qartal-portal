package com.nomis.application.dto;

import java.util.List;
import lombok.Data;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
@Data
public class SimulationJobs {
    List<Simulation> queued;
    List<Simulation> running;
}