package com.nomis.config;

import java.io.IOException;
import java.util.Properties;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@Slf4j
public class PropertiesConfig {

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
      throws IOException {

    PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

    ClassLoader cl = PropertiesConfig.class.getClassLoader();
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
    String activeProfile = System.getProperty("spring.profiles.active");

    //Collect all files to application.yml pattern and put them to resources
    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
    String pattern = "classpath*:application.yml";
    yaml.setResources(resolver.getResources(pattern));

    //Extract properties
    Properties properties = yaml.getObject();

    //If profile is set - collect all files to application + profile.yml pattern
    if (activeProfile != null && !activeProfile.contains("null")) {
      pattern = "classpath*:application-" + activeProfile + ".yml";

      //put them to resources
      yaml.setResources(resolver.getResources(pattern));
      Properties propertiesForProfile = yaml.getObject();

      //combine properties in order to prioritize test data first
      properties.putAll(propertiesForProfile);
    }

    configurer.setProperties(properties);
    return configurer;

  }
}
