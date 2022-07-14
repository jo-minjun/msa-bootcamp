package com.vroong.msabootcamp.service;

import com.vroong.msabootcamp.api.model.AlbumDto;
import com.vroong.msabootcamp.api.model.AlbumListDto;
import com.vroong.msabootcamp.api.model.CreateAlbumRequestDto;
import com.vroong.msabootcamp.api.model.PageDto;
import com.vroong.msabootcamp.domain.Album;
import com.vroong.msabootcamp.domain.Singer;
import com.vroong.msabootcamp.repository.AlbumRepository;
import com.vroong.msabootcamp.repository.SingerRepository;
import com.vroong.msabootcamp.service.mapper.AlbumMapper;
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
public class AlbumService {

  private final AlbumRepository albumRepository;
  private final SingerRepository singerRepository;
  private final AlbumMapper albumMapper;
  private final PersistentEventCreator persistentEventCreator;

  @Transactional
  public AlbumDto createAlbum(CreateAlbumRequestDto dto) {
    final Singer singer = singerRepository.findById(dto.getSingerId())
        .orElseThrow(() -> new NoSuchElementException("Not Found Singer: " + dto.getSingerId()));
    final Album album = Album.createFrom(dto, singer);

    final Album savedAlbum = albumRepository.save(album);
    persistentEventCreator.create("ALBUM_CREATED", savedAlbum);

    return albumMapper.toDto(savedAlbum);
  }

  @Transactional(readOnly = true)
  public AlbumListDto listAlbums(Pageable pageable) {
    final Page<Album> albumPage = albumRepository.searchListAlumPage(pageable);
    final List<Album> albums = albumPage.getContent();
    final PageDto pageDto = PaginationUtils.getPageDto(albumPage);

    return new AlbumListDto()
        .data(albumMapper.toDto(albums))
        .page(pageDto);
  }
}
