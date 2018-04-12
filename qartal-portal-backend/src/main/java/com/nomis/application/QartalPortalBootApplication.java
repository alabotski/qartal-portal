package com.nomis.application;

/**
 * QartalPortalBootApplication.
 *
 * @author Aliaksei Labotski.
 * @since 4/12/18.
 */

@SpringBootApplication(scanBasePackages = {"com.nomis"})
//@EnableAutoConfiguration
//@Slf4j
@SuppressWarnings("PMD.UseUtilityClass")
public class QartalPortalBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(QartalPortalBootApplication.class, args);
  }
}
