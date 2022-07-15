package com.vroong.msabootcamp.stream;

import com.vroong.msabootcamp.domain.Album;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageSubscriber {

  @StreamListener(value = ConsumerChannel.CHANNEL)
  public void subscribe1(Message<Album> event) {
    log.debug("Object Event received: {}", event.getPayload());
  }
}
