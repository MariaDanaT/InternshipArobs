package com.example.musify.mapper;

import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.dto.songdto.SongWithAlbumDTO;
import com.example.musify.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song songFromSongDTO(SongDTO songDTO);

    SongDTO songToSongDTO(Song song);

    @Mapping(target = "album", expression = "java(song.getAlbum().getId())")
    SongWithAlbumDTO songToSongWithAlbumDTO(Song song);

    @Mapping(target = "id", ignore = true)
    void mergeSongAndSongDTO(@MappingTarget Song song, SongDTO songDTO);
}
