package com.example.musify.mapper;

import com.example.musify.dto.artistdto.ArtistDTO;
import com.example.musify.entity.Artist;
import org.mapstruct.Mapper;

@Mapper
public interface ArtistMapper {
    ArtistDTO artistToArtistDTO(Artist artist);

    Artist artistFromArtistDTO(ArtistDTO artistDTO);
}
