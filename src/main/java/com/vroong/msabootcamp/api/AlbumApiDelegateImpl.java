package com.vroong.msabootcamp.api;

import com.vroong.msabootcamp.api.model.AlbumDto;
import com.vroong.msabootcamp.api.model.AlbumListDto;
import com.vroong.msabootcamp.api.model.CreateAlbumRequestDto;
import com.vroong.msabootcamp.service.AlbumService;
import com.vroong.msabootcamp.support.HeaderUtils;
import com.vroong.msabootcamp.support.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AlbumApiDelegateImpl implements AlbumApiDelegate {

  private final AlbumService albumService;

  @Override
  public ResponseEntity<Void> createAlbum(CreateAlbumRequestDto createAlbumRequestDto) {
    AlbumDto albumDto = albumService.createAlbum(createAlbumRequestDto);

    return ResponseEntity
        .created(HeaderUtils.uri(String.valueOf(albumDto.getAlbumId())))
        .build();
  }

  @Override
  public ResponseEntity<AlbumListDto> listAlbums(Integer page, Integer size) {
    final Pageable pageable = PaginationUtils.getPageable(page, size);
    final AlbumListDto body = albumService.listAlbums(pageable);

    return ResponseEntity.ok(body);
  }
}
