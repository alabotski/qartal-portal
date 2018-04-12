package com.nomis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    WebMvcConfigurer.super.addCorsMappings(registry);
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*");
  }

}
