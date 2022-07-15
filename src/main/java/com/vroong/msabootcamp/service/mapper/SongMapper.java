package com.vroong.msabootcamp.service.mapper;

import com.vroong.msabootcamp.api.model.SongDto;
import com.vroong.msabootcamp.domain.Song;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongMapper extends EntityMapper<SongDto, Song> {

  @Mapping(source = "id", target = "songId")
  @Override
  SongDto toDto(Song entity);

  @Mapping(source = "id", target = "songId")
  @Override
  List<SongDto> toDto(List<Song> entityList);
}
