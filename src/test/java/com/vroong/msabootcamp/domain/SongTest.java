package com.vroong.msabootcamp.domain;

import static com.vroong.msabootcamp.config.Constants.ZONE_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.vroong.msabootcamp.api.model.CreateAlbumRequestDto;
import com.vroong.msabootcamp.api.model.CreateSingerRequestDto;
import com.vroong.msabootcamp.api.model.CreateSongRequestDto;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SongTest {

  final CreateSingerRequestDto createSingerRequestDto = new CreateSingerRequestDto().name("singer");
  final CreateAlbumRequestDto createAlbumRequestDto = new CreateAlbumRequestDto()
      .singerId(any(Long.class))
      .title("album title")
      .publishedAt(OffsetDateTime.now(ZONE_ID));
  final CreateSongRequestDto createSongRequestDto = new CreateSongRequestDto()
      .albumId(any(Long.class))
      .title("song title")
      .playTime("03:00");

  Singer singer;
  Album album;
  Song song;

  @Test
  void update() {
    final String newSongTitle = "new song title";
    final CreateSongRequestDto newCreateSongRequestDto = new CreateSongRequestDto()
        .albumId(any(Long.class))
        .title(newSongTitle)
        .playTime("03:00");

    song.update(newCreateSongRequestDto, album);

    assertThat(song.getTitle()).isEqualTo(newSongTitle);
  }

  @Test
  void registerAlbum() {
    final String newAlbumTitle = "new album title";
    final CreateAlbumRequestDto newCreateAlbumRequestDto = new CreateAlbumRequestDto()
        .singerId(any(Long.class))
        .title(newAlbumTitle)
        .publishedAt(OffsetDateTime.now(ZONE_ID));
    final Album newAlbum = Album.createFrom(newCreateAlbumRequestDto, singer);

    song.registerAlbum(newAlbum);

    assertThat(song.getAlbum().getSongs().get(0)).isEqualTo(song);
  }

  @BeforeEach
  void setup() {
    singer = Singer.createFrom(createSingerRequestDto);

    album = Album.createFrom(createAlbumRequestDto, singer);

    song = Song.createFrom(createSongRequestDto, album);
  }
}