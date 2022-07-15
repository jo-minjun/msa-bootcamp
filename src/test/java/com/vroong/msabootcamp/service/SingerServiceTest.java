package com.vroong.msabootcamp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.vroong.msabootcamp.api.model.CreateSingerRequestDto;
import com.vroong.msabootcamp.api.model.SingerDto;
import com.vroong.msabootcamp.domain.Singer;
import com.vroong.msabootcamp.repository.SingerRepository;
import com.vroong.msabootcamp.service.mapper.SingerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = SpringExtension.class)
@ContextConfiguration
class SingerServiceTest {

  @InjectMocks
  SingerService singerService;
  @Mock
  SingerRepository singerRepository;
  @Mock
  SingerMapper singerMapper;

  @Test
  void createSinger() {
    final String name = "이름";
    final Long id = 1L;
    final CreateSingerRequestDto dto = new CreateSingerRequestDto()
        .name(name);
    final Singer singer = Singer.builder()
        .id(id)
        .name(name)
        .build();
    when(singerRepository.save(Singer.createFrom(dto))).thenReturn(singer);
    when(singerMapper.toDto(singer)).thenReturn(new SingerDto()
        .singerId(singer.getId())
        .name(singer.getName())
    );

    final SingerDto singerDto = singerService.createSinger(dto);

    assertThat(singerDto.getSingerId()).isEqualTo(id);
    assertThat(singerDto.getName()).isEqualTo(name);
  }
}