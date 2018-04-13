package com.nomis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootConfiguration
@Slf4j
public class WebConfig {

  @Bean
  public CorsFilter corsFilter() {
    final CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
    config.addAllowedMethod("*");
    config.addAllowedOrigin("*");

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

}
