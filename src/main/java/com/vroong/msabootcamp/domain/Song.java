package com.vroong.msabootcamp.domain;

import com.vroong.msabootcamp.api.model.CreateSongRequestDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "songs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "title", "playTime"})
@EqualsAndHashCode(of = {"id"})
public class Song extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 64)
  private String title;

  @Column(length = 8)
  private String playTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "album_id", foreignKey = @ForeignKey(name = "FK_SONG_TO_ALBUM"))
  private Album album;

  @Builder
  private Song(Long id, String title, String playTime) {
    this(title, playTime);
    this.id = id;
  }

  @Builder
  private Song(String title, String playTime) {
    this.title = title;
    this.playTime = playTime;
  }

  public static Song createFrom(CreateSongRequestDto dto, Album album) {
    final Song song = new Song(dto.getTitle(), dto.getPlayTime());
    song.registerAlbum(album);
    return song;
  }

  public void update(CreateSongRequestDto dto, Album album) {
    this.title = dto.getTitle();
    this.playTime = dto.getPlayTime();
    registerAlbum(album);
  }

  public void registerAlbum(Album album) {
    this.album = album;
    album.getSongs().add(this);
  }
}
