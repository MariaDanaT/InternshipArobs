package com.example.musify.service;

import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.entity.Song;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.springdata.SongRepository;
import com.example.musify.service.utilcheck.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Autowired
    public SongService(SongRepository songRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    @Transactional
    public SongDTO create(SongDTO songDTO) {
        Song song = songMapper.songFromSongDTO(songDTO);
        return songMapper.songToSongDTO(songRepository.save(song));
    }

    @Transactional
    public SongDTO update(SongDTO songDTO) {
        Song song = Checker.getSongIfExists(songRepository.findById(songDTO.getId()), songDTO.getId());
        songMapper.mergeSongAndSongDTO(song,songDTO);
        return songMapper.songToSongDTO(song);
    }

    @Transactional
    public Optional<SongDTO> getById(int id) {
        return Optional.ofNullable(songMapper.songToSongDTO(songRepository.getById(id)));
    }
}
