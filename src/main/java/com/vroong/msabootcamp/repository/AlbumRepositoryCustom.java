package com.vroong.msabootcamp.repository;

import com.vroong.msabootcamp.domain.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumRepositoryCustom {

  Page<Album> searchListAlumPage(Pageable pageable);
}
