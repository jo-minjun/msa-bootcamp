package com.vroong.msabootcamp.service.mapper;

import com.vroong.msabootcamp.api.model.AlbumDto;
import com.vroong.msabootcamp.domain.Album;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SingerMapper.class, SongMapper.class})
public interface AlbumMapper extends EntityMapper<AlbumDto, Album> {

  @Override
  @Mapping(source = "id", target = "albumId")
  AlbumDto toDto(Album entity);

  @Override
  @Mapping(source = "id", target = "albumId")
  List<AlbumDto> toDto(List<Album> entityList);
}
