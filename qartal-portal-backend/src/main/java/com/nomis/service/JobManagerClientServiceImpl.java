package com.nomis.service;

import static com.nomis.util.CommonConstants.BASELINES;
import static com.nomis.util.CommonConstants.SIMULATIONS;

import com.nomis.dto.BaselineJobs;
import com.nomis.dto.NodeDto;
import com.nomis.dto.SimulationJobs;
import com.nomis.shared.model.NodeName;
import com.nomis.shared.model.ServerStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
    jobManagerNode = nodesService.getNodeByNodeType(NodeName.Jobmanager.toString());

    restTemplate = new RestTemplateBuilder()
        .rootUri(propertyService.getJobManagerProtocol()
            + jobManagerNode.getIpAddress()
            + ":" + propertyService.getJobManagerPort()
            + propertyService.getJobManagerApiPath())
        .requestFactory(this::getClientHttpRequestFactory)
        .build();

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    executorService.scheduleAtFixedRate((this::pollForUpdate), 0, 1, TimeUnit.SECONDS);
  }

  private ClientHttpRequestFactory getClientHttpRequestFactory() {
    int timeout = 2000;
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
        new HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setConnectTimeout(timeout);
    return clientHttpRequestFactory;
  }

  private void pollForUpdate() {
    if (healthy()) {
      try {
        listAllSimulatedJobs();
        listAllBaselinesJobs();
        changeJobmanagerStatus();
      } catch (Exception ex) {
        log.error(ex.getMessage(), ex);
      }
    } else {
      jobManagerNode.setStatus(ServerStatus.DISABLED);
    }
  }

  private void changeJobmanagerStatus() {
    SimulationJobs simulationJobs = getSimulationJobs();
    BaselineJobs baselineJobs = getBaselineJobs();
    if (!simulationJobs.getQueued()
        .isEmpty()
        || !simulationJobs.getRunning()
        .isEmpty()
        || !baselineJobs.getQueued()
        .isEmpty()
        || !baselineJobs.getRunning()
        .isEmpty()) {
      jobManagerNode.setStatus(ServerStatus.RUNNING);
      //TODO implement status update within appropriate service
      nodesService.getNodeByNodeType("NPO").setStatus(ServerStatus.RUNNING);
      nodesService.getNodeListByNodeType("SERVICES").forEach(node -> node.setStatus(ServerStatus.RUNNING));
    } else {
      jobManagerNode.setStatus(ServerStatus.ENABLE);
      //TODO implement status update within appropriate service
      nodesService.getNodeByNodeType("NPO").setStatus(ServerStatus.ENABLE);
      nodesService.getNodeListByNodeType("SERVICES").forEach(node -> node.setStatus(ServerStatus.ENABLE));
    }

  }

  //TODO changeTo real healthCheck endpoint
  private boolean healthy() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    Map<String, Object> uriVars = new HashMap<>();
    HttpEntity<Object> entity = new HttpEntity<>(headers);

    try {
      ResponseEntity<String> responseEntity = restTemplate
          .exchange("/simulation/allSimulations/", HttpMethod.GET, entity, String.class, uriVars);
      return responseEntity.getStatusCode()
          .equals(HttpStatus.OK);
    } catch (Exception ex) {
      return false;
    }
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
    return (BaselineJobs) jobManagerNode.getNodeProperties()
        .get(BASELINES);
  }

  @Override
  public SimulationJobs getSimulationJobs() {
    return (SimulationJobs) jobManagerNode.getNodeProperties()
        .get(SIMULATIONS);
  }
}
