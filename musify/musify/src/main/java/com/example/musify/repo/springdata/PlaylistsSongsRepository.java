package com.example.musify.repo.springdata;

import com.example.musify.entity.Playlist;
import com.example.musify.entity.PlaylistsSongs;
import com.example.musify.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistsSongsRepository extends JpaRepository<PlaylistsSongs, Integer> {
    Integer countByPlaylist(Playlist playlist);

    PlaylistsSongs findBySongFromPlaylistAndPlaylist(Song song, Playlist playlist);
}
