package com.vroong.msabootcamp.repository;

import com.vroong.msabootcamp.domain.Song;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SongRepository extends JpaRepository<Song, Long> {

  @Query(value = "select song from Song song join fetch song.album where song.id = :songId")
  Optional<Song> findByIdFetch(@Param(value = "songId") Long songId);
}
