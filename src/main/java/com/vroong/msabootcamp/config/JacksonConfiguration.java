package com.vroong.msabootcamp.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.*;

@Configuration
public class JacksonConfiguration {

  @Bean
  public JsonNullableModule jsonNullableModule() {
    return new JsonNullableModule();
  }

  @Bean
  public JavaTimeModule javaTimeModule() {
    return new JavaTimeModule();
  }
}
