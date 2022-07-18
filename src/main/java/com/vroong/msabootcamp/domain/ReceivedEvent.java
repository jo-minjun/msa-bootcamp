package com.vroong.msabootcamp.domain;

import static com.vroong.msabootcamp.config.Constants.ZONE_ID;

import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "received_events")
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceivedEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 36)
  private UUID messageId;

  private OffsetDateTime receivedDateTime;

  public ReceivedEvent(UUID messageId) {
    this.messageId = messageId;
  }

  @PrePersist
  private void prePersist() {
    receivedDateTime = OffsetDateTime.now(ZONE_ID);
  }
}
