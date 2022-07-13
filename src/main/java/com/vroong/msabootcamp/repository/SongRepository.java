package com.vroong.msabootcamp.repository;

import com.vroong.msabootcamp.domain.Song;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SongRepository extends JpaRepository<Song, Long> {

  @Query(value = "select song from Song song join fetch song.album")
  Optional<Song> findByIdFetch(Long songId);
}
