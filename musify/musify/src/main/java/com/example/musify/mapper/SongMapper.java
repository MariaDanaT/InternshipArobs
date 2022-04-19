package com.example.musify.mapper;

import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.entity.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song songFromSongDTO(SongDTO songDTO);
    SongDTO songToSongDTO(Song song);
}
