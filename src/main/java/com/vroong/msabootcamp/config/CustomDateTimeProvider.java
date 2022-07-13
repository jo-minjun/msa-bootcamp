package com.vroong.msabootcamp.config;

import static com.vroong.msabootcamp.config.Constants.ZONE_ID;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomDateTimeProvider implements DateTimeProvider {

  @Override
  public Optional<TemporalAccessor> getNow() {
    return Optional.of(OffsetDateTime.now(ZONE_ID));
  }
}
