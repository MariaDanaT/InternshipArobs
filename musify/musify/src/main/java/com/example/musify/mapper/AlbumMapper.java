package com.example.musify.mapper;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumDTO albumToAlbumDTO(Album album);

    Album albumFromAlbumDTO(AlbumDTO albumDTO);

    @Mapping(target = "id", ignore = true)
    void mergeAlbumAndAlbumDTO(@MappingTarget Album album, AlbumDTO albumDTO);
}
