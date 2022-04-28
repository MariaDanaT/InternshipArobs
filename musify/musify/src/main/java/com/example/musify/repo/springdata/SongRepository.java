package com.example.musify.repo.springdata;

import com.example.musify.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT s FROM Song s JOIN s.playlistsSongs ps" +
            " WHERE ps.playlist.id =:idPlaylist")
    List<Song> findAll(@Param("idPlaylist") Integer idPlaylist);
}
