package com.nomis.service;

import com.nomis.dto.BaselineJobs;
import com.nomis.dto.SimulationJobs;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * JobManagerClientServiceImpl.
 *
 * @author Aliaksei Labotski.
 * @since 4/17/18.
 */
@Slf4j
@Service
public class JobManagerClientServiceImpl implements JobManagerClientService {

  private BaselineJobs baselineJobs;
  private SimulationJobs simulationJobs;

  @Inject
  private PropertyService propertyService;

  @PostConstruct
  public void init() {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    executorService.scheduleAtFixedRate((this::pollForUpdate), 0, 1, TimeUnit.SECONDS);
  }

  private void pollForUpdate() {
    simulationJobs = listAllSimulatedJobs();
    baselineJobs = listAllBaselinesJobs();
  }

  private SimulationJobs listAllSimulatedJobs() {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(propertyService.getConnectUrl() + "/simulation/allSimulations/",
        SimulationJobs.class);
  }

  private BaselineJobs listAllBaselinesJobs() {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(propertyService.getConnectUrl() + "/baseline/allBaselines", BaselineJobs.class);
  }

  @Override
  public BaselineJobs getBaselineJobs() {
    return baselineJobs;
  }

  @Override
  public SimulationJobs getSimulationJobs() {
    return simulationJobs;
  }
}
