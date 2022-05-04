package com.example.musify.service;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.dto.songdto.SongWithAlbumDTO;
import com.example.musify.entity.Album;
import com.example.musify.entity.Song;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.springdata.AlbumRepository;
import com.example.musify.repo.springdata.SongRepository;
import com.example.musify.service.utilcheck.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
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
        Album album = Checker.getAlbumIfExists(albumRepository.findById(albumDTO.getId()), albumDTO.getId());
        albumMapper.mergeAlbumAndAlbumDTO(album, albumDTO);

        return albumMapper.albumToAlbumDTO(album);
    }

    @Transactional
    public List<SongWithAlbumDTO> addSongToAlbum(Integer idSong, Integer idAlbum) {
        Song song = Checker.getSongIfExists(songRepository.findById(idSong), idSong);
        Album album = Checker.getAlbumIfExists(albumRepository.findById(idAlbum), idAlbum);
        song.setAlbum(album);
        album.addSongToAlbum(song);
        return songRepository.findAll()
                .stream()
                .map(songMapper::songToSongWithAlbumDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SongDTO> allSongsForAlbum(Integer idAlbum) {
        Album album = Checker.getAlbumIfExists(albumRepository.findById(idAlbum), idAlbum);
        return album.getSongs()
                .stream()
                .sorted(Comparator.comparing(Song::getId))
                .map(songMapper::songToSongDTO)
                .collect(Collectors.toList());
    }
}
