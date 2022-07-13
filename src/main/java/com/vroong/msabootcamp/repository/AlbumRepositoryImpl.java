package com.vroong.msabootcamp.repository;

import static com.vroong.msabootcamp.domain.QAlbum.album;
import static com.vroong.msabootcamp.domain.QSinger.singer;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vroong.msabootcamp.domain.Album;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class AlbumRepositoryImpl implements AlbumRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Album> searchListAlumPage(Pageable pageable) {
    List<Album> albums = queryFactory.selectFrom(album)
        .join(album.singer, singer)
        .fetchJoin()
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    return new PageImpl<>(albums, pageable, albums.size());
  }
}
