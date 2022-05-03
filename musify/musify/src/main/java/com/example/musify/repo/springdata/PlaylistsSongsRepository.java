package com.example.musify.repo.springdata;

import com.example.musify.entity.Playlist;
import com.example.musify.entity.PlaylistsSongs;
import com.example.musify.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistsSongsRepository extends JpaRepository<PlaylistsSongs, Integer> {
    Integer countByPlaylist(Playlist playlist);

    PlaylistsSongs findBySongFromPlaylistAndPlaylist(Song song, Playlist playlist);

    @Query("SELECT songFromPlaylist FROM PlaylistsSongs ps" +
            " WHERE ps.playlist.id =:idPlaylist" +
            " ORDER BY ps.orderNumber ASC")
    List<Song> findAll(@Param("idPlaylist") Integer idPlaylist);
}
