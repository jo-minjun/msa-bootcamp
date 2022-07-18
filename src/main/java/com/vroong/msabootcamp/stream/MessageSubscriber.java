package com.vroong.msabootcamp.stream;

import com.vroong.msabootcamp.domain.Album;
import com.vroong.msabootcamp.domain.ReceivedEvent;
import com.vroong.msabootcamp.repository.ReceivedEventRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class MessageSubscriber {

  private final ReceivedEventRepository receivedEventRepository;

  @StreamListener(value = ConsumerChannel.CHANNEL)
  public void subscribe(Message<Album> event) {
    UUID messageId = event.getHeaders().getId();
    Optional<ReceivedEvent> receivedEvent = receivedEventRepository.findByMessageId(messageId);

    if (receivedEvent.isPresent()) {
      log.info("Duplicated Event: {}", receivedEvent.get().getMessageId());
      return;
    }

    log.debug("Event received: {}", event.getPayload());
  }
}
