package com.example.musify.service;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.entity.Album;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.repo.springdata.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
    }

    @Transactional
    public Optional<AlbumDTO> getById(int id) {
        return Optional.ofNullable(albumMapper.albumToAlbumDTO(albumRepository.getById(id)));
    }

    @Transactional
    public AlbumDTO create(AlbumDTO albumDTO) {
        Album album = albumMapper.albumFromAlbumDTO(albumDTO);
        return albumMapper.albumToAlbumDTO(albumRepository.save(album));
    }

    @Transactional
    public AlbumDTO update(AlbumDTO albumDTO) {
        Optional<Album> optionalAlbum = albumRepository.findById(albumDTO.getId());
        if (optionalAlbum.isEmpty()) {
            throw new ResourceNotFoundException("There is no album with id = " + albumDTO.getId());
        }
        Album album = optionalAlbum.get();
        album.setTitle(albumDTO.getTitle());
        album.setDescription(albumDTO.getDescription());
        album.setGenre(albumDTO.getGenre());
        album.setReleaseDate(albumDTO.getReleaseDate());
        album.setLabel(albumDTO.getLabel());

        return albumMapper.albumToAlbumDTO(album);
    }
}
