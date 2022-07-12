package com.vroong.msabootcamp.api.fixtures;

import com.vroong.msabootcamp.api.model.AlbumDto;
import com.vroong.msabootcamp.api.model.PageDto;
import com.vroong.msabootcamp.api.model.SingerDto;
import com.vroong.msabootcamp.api.model.SongDto;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.UUID;

public class Fixture {

  public static final UUID DEFAULT_UUID = UUID.randomUUID();
  public static final OffsetDateTime DEFAULT_DATETIME = OffsetDateTime.parse("1993-03-01T09:00:00+09:00");
  public static final int DEFAULT_PAGE_SIZE = 5;
  public static final int DEFAULT_PAGE_NUMBER = 1;
  public static final long DEFAULT_TOTAL_ELEMENTS_SIZE = 1L;
  public static final int DEFAULT_TOTAL_PAGE_SIZE = 1;

  public static AlbumDto anAlbumDto(){
    return new AlbumDto()
        .albumId(1L)
        .title("다시 부르기")
        .publishedAt(DEFAULT_DATETIME)
        .singer(Fixture.aSingerDto())
        .songs(Collections.singletonList(Fixture.aSongDto()))
        .createdAt(DEFAULT_DATETIME)
        .updatedAt(DEFAULT_DATETIME)
        .createdBy(DEFAULT_UUID)
        .updatedBy(DEFAULT_UUID);
  }

  public static SingerDto aSingerDto() {
    return new SingerDto()
        .singerId(1L)
        .name("김광석")
        .createdAt(DEFAULT_DATETIME)
        .updatedAt(DEFAULT_DATETIME)
        .createdBy(DEFAULT_UUID)
        .updatedBy(DEFAULT_UUID);
  }

  public static SongDto aSongDto() {
    return new SongDto()
        .songId(1L)
        .title("두바퀴로 가는 자동차")
        .playTime("03:20")
        .createdAt(DEFAULT_DATETIME)
        .updatedAt(DEFAULT_DATETIME)
        .createdBy(DEFAULT_UUID)
        .updatedBy(DEFAULT_UUID);
  }

  public static PageDto aPageDto() {
    return new PageDto()
        .number(DEFAULT_PAGE_NUMBER)
        .size(DEFAULT_PAGE_SIZE)
        .totalPages(DEFAULT_TOTAL_PAGE_SIZE)
        .totalElements(DEFAULT_TOTAL_ELEMENTS_SIZE);
  }
}
