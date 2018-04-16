package com.nomis.application;

import com.nomis.application.config.QuartalAppConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * QartalPortalBootApplication.
 *
 * @author Aliaksei Labotski.
 * @since 4/12/18.
 */

@SpringBootApplication(scanBasePackages = {"com.nomis"})
@EnableScheduling
@Slf4j
@SuppressWarnings("PMD.UseUtilityClass")
@Import(QuartalAppConfiguration.class)
public class QartalPortalBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(QartalPortalBootApplication.class, args);
  }

}
