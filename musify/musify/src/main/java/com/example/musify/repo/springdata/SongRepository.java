package com.example.musify.repo.springdata;

import com.example.musify.entity.Song;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    @Query("SELECT s FROM Song s WHERE s.title iLIKE %:title%")
    List<Song> findByTitleContainingIgnoreCase(@Param("title") String title);
}
