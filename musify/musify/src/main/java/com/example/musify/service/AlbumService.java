package com.example.musify.service;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.dto.songdto.SongWithAlbumDTO;
import com.example.musify.entity.Album;
import com.example.musify.entity.Song;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.springdata.AlbumRepository;
import com.example.musify.repo.springdata.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final AlbumMapper albumMapper;
    private final SongMapper songMapper;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, SongRepository songRepository, AlbumMapper albumMapper, SongMapper songMapper) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.albumMapper = albumMapper;
        this.songMapper = songMapper;
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

    @Transactional
    public List<SongWithAlbumDTO> addSongToAlbum(Integer idSong, Integer idAlbum) {
        Optional<Song> optionalSong = songRepository.findById(idSong);
        Optional<Album> albumOptional = albumRepository.findById(idAlbum);
        if (optionalSong.isEmpty())
            throw new ResourceNotFoundException("There is no song with id = " + idSong);
        if (albumOptional.isEmpty()) {
            throw new ResourceNotFoundException("There is no album with id = " + idAlbum);
        }
        Song song = optionalSong.get();
        Album album = albumOptional.get();
        song.setAlbum(album);
        album.addSongToAlbum(song);
        return songRepository.findAll()
                .stream()
                .map(song1 -> songMapper.songToSongWithAlbumDTO(song1))
                .collect(Collectors.toList());
    }
}
