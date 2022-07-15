package com.vroong.msabootcamp.service;

import static com.vroong.msabootcamp.api.fixtures.Fixture.DEFAULT_PAGE_NUMBER;
import static com.vroong.msabootcamp.api.fixtures.Fixture.DEFAULT_PAGE_SIZE;
import static com.vroong.msabootcamp.config.Constants.ZONE_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = SpringExtension.class)
class AlbumServiceTest {

  @InjectMocks
  AlbumService albumService;
  @Mock
  AlbumRepository albumRepository;
  @Mock
  SingerRepository singerRepository;
  @Mock
  AlbumMapper albumMapper;
  @Mock
  PersistentEventCreator persistentEventCreator;

  @Test
  void createAlbum() {
    final OffsetDateTime publishedAt = OffsetDateTime.now(ZONE_ID);
    final String title = "title";
    final long id = 1L;
    final CreateAlbumRequestDto dto = new CreateAlbumRequestDto()
        .singerId(id)
        .title(title)
        .publishedAt(publishedAt);
    final Singer singer = Singer.builder().id(id).name("name").build();
    final Album album = Album.createFrom(dto, singer);
    final Album savedAlbum = Album.builder().id(id).build();
    when(singerRepository.findById(id)).thenReturn(Optional.ofNullable(singer));
    when(albumRepository.save(album)).thenReturn(savedAlbum);
    when(albumMapper.toDto(savedAlbum)).thenReturn(new AlbumDto().albumId(savedAlbum.getId()));
    doNothing().when(persistentEventCreator).create(any(), any());

    final AlbumDto albumDto = albumService.createAlbum(dto);

    assertThat(albumDto.getAlbumId()).isEqualTo(id);
  }

  @Test
  void listAlbums() {
    final Pageable pageable = PaginationUtils.getPageable(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    final long id = 1L;
    final Album album = Album.builder().id(id).build();
    final PageImpl<Album> albumPage = new PageImpl<>(
        Collections.singletonList(album),
        pageable,
        1
    );
    final List<Album> content = albumPage.getContent();
    when(albumRepository.searchListAlumPage(pageable)).thenReturn(albumPage);
    when(albumMapper.toDto(content))
        .thenReturn(Collections.singletonList(new AlbumDto().albumId(album.getId())));

    final AlbumListDto albumListDto = albumService.listAlbums(pageable);
    final List<AlbumDto> data = albumListDto.getData();
    final PageDto page = albumListDto.getPage();

    assertThat(data.size()).isEqualTo(content.size());
    assertThat(data.get(0).getAlbumId()).isEqualTo(album.getId());
    assertThat(page.getTotalElements()).isEqualTo(content.size());
  }
}