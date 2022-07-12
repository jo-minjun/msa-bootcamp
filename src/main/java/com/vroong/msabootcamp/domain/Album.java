package com.vroong.msabootcamp.domain;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "albums")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "publishedAt", "songs"})
@EqualsAndHashCode(of = {"id"})
public class Album extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private OffsetDateTime publishedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "singer_id", foreignKey = @ForeignKey(name = "FK_ALBUM_TO_SINGER"))
  private Singer singer;

  @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
  private List<Song> songs = new ArrayList<>();
}
