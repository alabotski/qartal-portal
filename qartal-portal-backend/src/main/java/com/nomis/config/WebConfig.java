package com.nomis.config;

@SpringBootConfiguration
//@Slf4j
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    WebMvcConfigurer.super.addCorsMappings(registry);
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*");
  }

}
