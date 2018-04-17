package com.nomis.shared.model;

import java.util.Random;

/**
 * ServerStatus.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
public enum ServerStatus {
  ENABLE, DISABLED, RUNNING;

  public static ServerStatus getRandomStatus() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
  }
}
