package com.nomis.service;

import com.nomis.dto.BaselineJobs;
import com.nomis.dto.SimulationJobs;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
@Log4j2
@Service
@Data
public class JobManagerClientService {

  private BaselineJobs baselineJobs;

  private SimulationJobs simulationJobs;

  @Inject
  private PropertyService propertyService;

  private ScheduledExecutorService executorService;

  @PostConstruct
  public void init() {
    executorService = Executors.newScheduledThreadPool(2);
    executorService.scheduleAtFixedRate((this::pollForUpdate), 0, 1, TimeUnit.SECONDS);
  }

  private void pollForUpdate() {

    simulationJobs = listAllSimulatedJobs();
//    log.info(simulationJobs);

    baselineJobs = listAllBaselinesJobs();
//    log.info(baselineJobs);
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
}