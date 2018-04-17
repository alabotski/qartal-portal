package com.nomis.rabbit.converter;

import lombok.extern.slf4j.Slf4j;

/**
 * ActionType.
 *
 * @author Alexander Sokolov.
 */
@Slf4j
public enum ActionType {

  SIMULATION, OPTIMIZATION, BASELINE, NOMISSERVICES, CONFIG, STATUS, ERROR, LOG, CANCEL, UNDEFINED;

  /**
   * Detects type of current request based on request routing key.
   *
   * @param routingKey routing key from incoming request
   * @return {@link ActionType} of current request
   */
  public static ActionType getFromRoutingKey(String routingKey) {
    try {
      return ActionType.valueOf(routingKey.toUpperCase()
          .substring(routingKey.lastIndexOf('.') + 1));
    } catch (IllegalArgumentException e) {
      log.error("getFromRoutingKey", e);
      return UNDEFINED;
    }
  }

}
