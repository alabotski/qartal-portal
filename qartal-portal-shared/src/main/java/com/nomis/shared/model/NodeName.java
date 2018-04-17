package com.nomis.shared.model;

/**
 * NodeName.
 *
 * @author Eugene Tsydzik.
 * @since 4/17/18.
 */
public enum NodeName {

  NPO("NPO"),
  Jobmanager("JM"),
  RabbitMQ("RabbitMQ"),
  Service("Services");

  private final String text;

  /**
   * @param text
   */
  NodeName(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
