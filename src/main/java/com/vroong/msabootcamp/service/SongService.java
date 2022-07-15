package com.vroong.msabootcamp.service;

import com.vroong.msabootcamp.api.model.CreateSongRequestDto;
import com.vroong.msabootcamp.api.model.PageDto;
import com.vroong.msabootcamp.api.model.SongDto;
import com.vroong.msabootcamp.api.model.SongListDto;
import com.vroong.msabootcamp.domain.Album;
import com.vroong.msabootcamp.domain.Song;
import com.vroong.msabootcamp.repository.AlbumRepository;
import com.vroong.msabootcamp.repository.SongRepository;
import com.vroong.msabootcamp.service.mapper.SongMapper;
import com.vroong.msabootcamp.support.PaginationUtils;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SongService {

  private final SongRepository songRepository;
  private final AlbumRepository albumRepository;
  private final SongMapper songMapper;

  @Transactional
  public SongDto createSong(CreateSongRequestDto dto) {
    final Album album = albumRepository.findById(dto.getAlbumId())
        .orElseThrow(() -> new NoSuchElementException("Not Found Album: " + dto.getAlbumId()));
    final Song song = Song.createFrom(dto, album);

    final Song savedSong = songRepository.save(song);

    return songMapper.toDto(savedSong);
  }

  @Transactional
  public void deleteSong(Long songId) {
    songRepository.findById(songId)
        .ifPresent(songRepository::delete);
  }

  @Transactional(readOnly = true)
  public SongDto getSong(Long songId) {
    final Song song = songRepository.findById(songId)
        .orElseThrow(() -> new NoSuchElementException("Not Found Song: " + songId));

    return songMapper.toDto(song);
  }

  @Transactional(readOnly = true)
  public SongListDto listSongs(Pageable pageable) {
    final Page<Song> songPage = songRepository.findAll(pageable);
    final List<Song> songs = songPage.getContent();
    final PageDto pageDto = PaginationUtils.getPageDto(songPage);

    return new SongListDto()
        .data(songMapper.toDto(songs))
        .page(pageDto);
  }

  @Transactional
  public void updateSong(Long songId, CreateSongRequestDto dto) {
    final Song song = songRepository.findByIdFetch(songId)
        .orElseThrow(() -> new NoSuchElementException(
            "Not Found Song: " + songId + " at Album: " + dto.getAlbumId())
        );

    song.update(dto, song.getAlbum());
  }
}
