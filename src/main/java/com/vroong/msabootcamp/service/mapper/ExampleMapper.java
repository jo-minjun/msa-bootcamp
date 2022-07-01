package com.vroong.msabootcamp.service.mapper;

import com.vroong.msabootcamp.api.model.ExampleDto;
import com.vroong.msabootcamp.domain.Example;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface ExampleMapper extends EntityMapper<ExampleDto, Example>{

  @Override
  @Mapping(source = "id", target = "exampleId")
  ExampleDto toDto(Example entity);
}
