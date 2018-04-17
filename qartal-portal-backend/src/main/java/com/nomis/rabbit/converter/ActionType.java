package com.nomis.rabbit.converter;

/**
 * @author Alexander Sokolov
 */
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
      return ActionType.valueOf(
          routingKey.toUpperCase()
              .substring(routingKey.lastIndexOf(".") + 1));
    } catch (IllegalArgumentException e) {
      return UNDEFINED;
    }
  }

}
