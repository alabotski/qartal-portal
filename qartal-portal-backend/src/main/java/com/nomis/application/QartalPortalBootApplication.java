package com.nomis.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * QartalPortalBootApplication.
 *
 * @author Aliaksei Labotski.
 * @since 4/12/18.
 */

@SpringBootApplication(scanBasePackages = {"com.nomis"})
@Slf4j
@SuppressWarnings("PMD.UseUtilityClass")
public class QartalPortalBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(QartalPortalBootApplication.class, args);
  }
}
