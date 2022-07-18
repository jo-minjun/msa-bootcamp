package com.vroong.msabootcamp.repository;

import com.vroong.msabootcamp.domain.ReceivedEvent;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivedEventRepository extends JpaRepository<ReceivedEvent, Long> {

  Optional<ReceivedEvent> findByMessageId(UUID eventId);
}
