package com.vroong.msabootcamp.api;

import com.vroong.msabootcamp.api.model.CreateSongRequestDto;
import com.vroong.msabootcamp.api.model.SongDto;
import com.vroong.msabootcamp.api.model.SongListDto;
import com.vroong.msabootcamp.service.SongService;
import com.vroong.msabootcamp.support.HeaderUtils;
import com.vroong.msabootcamp.support.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SongApiDelegateImpl implements SongApiDelegate {

  private final SongService songService;

  @Override
  public ResponseEntity<Void> createSong(CreateSongRequestDto createSongRequestDto) {
    final SongDto songDto = songService.createSong(createSongRequestDto);

    return ResponseEntity
        .created(HeaderUtils.uri(String.valueOf(songDto.getSongId())))
        .build();
  }

  @Override
  public ResponseEntity<Void> deleteSong(Long songId) {
    songService.deleteSong(songId);

    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<SongDto> getSong(Long songId) {
    final SongDto songDto = songService.getSong(songId);

    return ResponseEntity.ok(songDto);
  }

  @Override
  public ResponseEntity<SongListDto> listSongs(Integer page, Integer size) {
    final Pageable pageable = PaginationUtils.getPageable(page, size);
    SongListDto songListDto = songService.listSongs(pageable);

    return ResponseEntity.ok(songListDto);
  }

  @Override
  public ResponseEntity<Void> updateSong(Long songId, CreateSongRequestDto createSongRequestDto) {
    songService.updateSong(songId, createSongRequestDto);

    return ResponseEntity.noContent().build();
  }
}
