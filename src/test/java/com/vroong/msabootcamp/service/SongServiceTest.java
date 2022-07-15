package com.vroong.msabootcamp.service;

import static com.vroong.msabootcamp.api.fixtures.Fixture.DEFAULT_PAGE_NUMBER;
import static com.vroong.msabootcamp.api.fixtures.Fixture.DEFAULT_PAGE_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vroong.msabootcamp.api.model.CreateSongRequestDto;
import com.vroong.msabootcamp.api.model.SongDto;
import com.vroong.msabootcamp.api.model.SongListDto;
import com.vroong.msabootcamp.domain.Album;
import com.vroong.msabootcamp.domain.Song;
import com.vroong.msabootcamp.repository.AlbumRepository;
import com.vroong.msabootcamp.repository.SongRepository;
import com.vroong.msabootcamp.service.mapper.SongMapper;
import com.vroong.msabootcamp.support.PaginationUtils;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = SpringExtension.class)
class SongServiceTest {

  @InjectMocks
  SongService songService;
  @Mock
  SongRepository songRepository;
  @Mock
  AlbumRepository albumRepository;
  @Mock
  SongMapper songMapper;

  @Test
  void createSong() {
    final long id = 1L;
    final String title = "title";
    final String playTime = "03:00";
    CreateSongRequestDto dto = new CreateSongRequestDto()
        .title(title)
        .playTime(playTime)
        .albumId(id);
    when(albumRepository.findById(id)).thenReturn(Optional.ofNullable(any(Album.class)));
    when(songRepository.save(any(Song.class))).thenReturn(any(Song.class));
    when(songMapper.toDto(any(Song.class)))
        .thenReturn(new SongDto().songId(id).title(title).playTime(playTime));

    final SongDto songDto = songService.createSong(dto);

    assertThat(songDto.getSongId()).isEqualTo(id);
    assertThat(songDto.getTitle()).isEqualTo(title);
    assertThat(songDto.getPlayTime()).isEqualTo(playTime);
  }

  @Test
  void deleteSong() {
    final long id = 1L;
    when(songRepository.findById(id)).thenReturn(Optional.ofNullable(any()));

    songService.deleteSong(id);

    verify(songRepository, times(1)).delete(any());
  }

  @Test
  void getSong() {
    final long id = 1L;
    String title = "title";
    when(songRepository.findById(id)).thenReturn(Optional.of(any(Song.class)));
    when(songMapper.toDto(any(Song.class))).thenReturn(new SongDto().songId(id).title(title));

    SongDto songDto = songService.getSong(id);

    assertThat(songDto.getSongId()).isEqualTo(id);
    assertThat(songDto.getTitle()).isEqualTo(title);
  }

  @Test
  void listSongs() {
    final Pageable pageable = PaginationUtils.getPageable(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    final long id = 1L;
    when(songRepository.findAll()).thenReturn(anyList());
    when(songMapper.toDto(anyList()))
        .thenReturn(Collections.singletonList(new SongDto().songId(id)));

    final SongListDto songListDto = songService.listSongs(pageable);
    final List<SongDto> data = songListDto.getData();

    assertThat(data.size()).isEqualTo(1);
    assertThat(data.get(0).getSongId()).isEqualTo(id);
  }
}