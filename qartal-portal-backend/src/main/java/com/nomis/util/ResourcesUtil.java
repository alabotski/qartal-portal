package com.nomis.util;

import java.io.InputStream;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * ResourcesUtil.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@Slf4j
public class ResourcesUtil {

  private static volatile ResourcesUtil instance;
  private static volatile Object lock = new Object();

  public static synchronized ResourcesUtil getInstance() {
    if (instance == null) {
      synchronized (lock) {
        instance = new ResourcesUtil();
      }
    }
    return instance;
  }

  public InputStream getResource(String fileName) {
    //    log.info("File name = " + fileName);
    return ResourcesUtil.class.getClassLoader()
        .getResourceAsStream(fileName);
  }
}
