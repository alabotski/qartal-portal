package com.nomis.service;

import static com.nomis.util.CommonConstants.BASELINES;
import static com.nomis.util.CommonConstants.SIMULATIONS;

import com.nomis.dto.BaselineJobs;
import com.nomis.dto.NodeDto;
import com.nomis.dto.SimulationJobs;
import com.nomis.util.CommonConstants;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
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


  @Inject
  private NodesService nodesService;

  private NodeDto jobManagerNode;
  private RestTemplate restTemplate;

  @Inject
  private PropertyService propertyService;

  @PostConstruct
  public void init() {
    jobManagerNode = nodesService.getNodeByNodeType("JM");

    restTemplate = new RestTemplateBuilder()
        .rootUri(propertyService.getJobManagerProtocol()
            + jobManagerNode.getIpAddress()
            + ":" + propertyService.getJobManagerPort()
            + propertyService.getJobManagerApiPath())
        .build();

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    executorService.scheduleAtFixedRate((this::pollForUpdate), 0, 1, TimeUnit.SECONDS);
  }

  private void pollForUpdate() {
    listAllSimulatedJobs();
    listAllBaselinesJobs();
  }

  private void listAllSimulatedJobs() {
    SimulationJobs simulations = restTemplate
        .getForObject("/simulation/allSimulations/", SimulationJobs.class);
    jobManagerNode.addProperty(SIMULATIONS, simulations);
  }

  private void listAllBaselinesJobs() {
    BaselineJobs baseLines = restTemplate
        .getForObject("/baseline/allBaselines", BaselineJobs.class);
    jobManagerNode.addProperty(BASELINES, baseLines);
  }

  @Override
  public BaselineJobs getBaselineJobs() {
    return (BaselineJobs) jobManagerNode.getNodeProperties().get(BASELINES);
  }

  @Override
  public SimulationJobs getSimulationJobs() {
    return (SimulationJobs) jobManagerNode.getNodeProperties().get(SIMULATIONS);
  }
}
