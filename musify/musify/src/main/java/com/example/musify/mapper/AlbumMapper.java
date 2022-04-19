package com.example.musify.mapper;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.entity.Album;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumDTO albumToAlbumDTO(Album album);
    Album albumFromAlbumDTO(AlbumDTO albumDTO);
}
