package com.example.musify.repo.springdata;

import com.example.musify.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findByTitleContainingIgnoreCase(String title);
}
