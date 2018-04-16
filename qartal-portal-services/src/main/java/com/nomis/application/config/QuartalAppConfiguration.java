package com.nomis.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
@Configuration
@ComponentScan("com.nomis.application")
@Import({PropertiesConfig.class, JobmanagerWebSocketConfig.class, RabbitConfiguration.class})
public class QuartalAppConfiguration {
}