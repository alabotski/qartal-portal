package com.nomis.service;

import com.nomis.dto.BaselineJobs;
import com.nomis.dto.SimulationJobs;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */

public interface JobManagerClientService {

  BaselineJobs getBaselineJobs();

  SimulationJobs getSimulationJobs();
}