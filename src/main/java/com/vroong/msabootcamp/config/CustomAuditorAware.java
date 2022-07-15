package com.vroong.msabootcamp.config;

import static com.vroong.msabootcamp.config.Constants.UNKNOWN_USER_ID;

import com.vroong.msabootcamp.support.SecurityUtils;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class CustomAuditorAware implements AuditorAware<UUID> {

  @Override
  public Optional<UUID> getCurrentAuditor() {
    String username = SecurityUtils.getCurrentUserLogin().orElse(UNKNOWN_USER_ID);
    return Optional.of(UUID.nameUUIDFromBytes(username.getBytes()));
  }
}
