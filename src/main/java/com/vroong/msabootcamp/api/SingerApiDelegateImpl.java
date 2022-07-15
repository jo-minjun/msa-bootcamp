package com.vroong.msabootcamp.api;

import com.vroong.msabootcamp.api.model.CreateSingerRequestDto;
import com.vroong.msabootcamp.api.model.SingerDto;
import com.vroong.msabootcamp.service.SingerService;
import com.vroong.msabootcamp.support.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SingerApiDelegateImpl implements SingerApiDelegate {

  private final SingerService singerService;

  @Override
  public ResponseEntity<Void> createSinger(CreateSingerRequestDto createSingerRequestDto) {
    SingerDto singerDto = singerService.createSinger(createSingerRequestDto);

    return ResponseEntity
        .created(HeaderUtils.uri(String.valueOf(singerDto.getSingerId())))
        .build();
  }
}
