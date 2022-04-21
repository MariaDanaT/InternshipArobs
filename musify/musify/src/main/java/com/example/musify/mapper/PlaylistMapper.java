package com.example.musify.mapper;

import com.example.musify.dto.playlistdto.PlaylistDTO;
import com.example.musify.entity.Playlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    Playlist playlistFromPlaylistDTO(PlaylistDTO playlistDTO);

    PlaylistDTO playlistToPlaylistDTO(Playlist playlist);
}
