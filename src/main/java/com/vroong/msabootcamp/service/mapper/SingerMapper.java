package com.vroong.msabootcamp.service.mapper;

import com.vroong.msabootcamp.api.model.SingerDto;
import com.vroong.msabootcamp.domain.Singer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SingerMapper extends EntityMapper<SingerDto, Singer> {

  @Override
  @Mapping(source = "id", target = "singerId")
  SingerDto toDto(Singer entity);
}
