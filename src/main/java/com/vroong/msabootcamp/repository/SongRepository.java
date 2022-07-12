package com.vroong.msabootcamp.repository;

import com.vroong.msabootcamp.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {

}
