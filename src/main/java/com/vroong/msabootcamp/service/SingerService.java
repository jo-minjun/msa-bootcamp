package com.vroong.msabootcamp.service;

import com.vroong.msabootcamp.api.model.CreateSingerRequestDto;
import com.vroong.msabootcamp.api.model.SingerDto;
import com.vroong.msabootcamp.domain.Singer;
import com.vroong.msabootcamp.repository.SingerRepository;
import com.vroong.msabootcamp.service.mapper.SingerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SingerService {

  private final SingerRepository singerRepository;
  private final SingerMapper singerMapper;

  @Transactional
  public SingerDto createSinger(CreateSingerRequestDto dto) {
    final Singer singer = Singer.createFrom(dto);
    final Singer savedSinger = singerRepository.save(singer);

    return singerMapper.toDto(savedSinger);
  }
}
