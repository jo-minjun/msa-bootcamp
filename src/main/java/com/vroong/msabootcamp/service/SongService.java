package com.vroong.msabootcamp.service;

import com.vroong.msabootcamp.api.model.CreateSongRequestDto;
import com.vroong.msabootcamp.api.model.SongDto;
import com.vroong.msabootcamp.api.model.SongListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SongService {

  public SongDto createSong(CreateSongRequestDto createSongRequestDto) {
    return null;
  }

  public void deleteSong(Long songId) {
  }

  public SongDto getSong(Long songId) {
    return null;
  }

  public SongListDto listSongs(Pageable pageable) {
    return null;
  }

  public void updateSong(Long songId, CreateSongRequestDto createSongRequestDto) {

  }
}
