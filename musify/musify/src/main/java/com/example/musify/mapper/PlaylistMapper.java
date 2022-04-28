package com.example.musify.mapper;

import com.example.musify.dto.playlistdto.PlaylistDTO;
import com.example.musify.dto.playlistdto.PlaylistWithSongsTitleDTO;
import com.example.musify.entity.Playlist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    Playlist playlistFromPlaylistDTO(PlaylistDTO playlistDTO);

    PlaylistDTO playlistToPlaylistDTO(Playlist playlist);

    PlaylistWithSongsTitleDTO playlistWithTheirSongsTitle(Integer idPlaylist, List<String> songsTitle);
}
