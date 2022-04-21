package com.example.musify.service;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.dto.persondto.PersonDTO;
import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.entity.Album;
import com.example.musify.entity.Person;
import com.example.musify.entity.Song;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.mapper.PersonMapper;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.springdata.AlbumRepository;
import com.example.musify.repo.springdata.PersonRepository;
import com.example.musify.repo.springdata.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchService {

    private final SongRepository songRepository;
    private final PersonRepository personRepository;
    private final AlbumRepository albumRepository;
    private final SongMapper songMapper;
    private final PersonMapper personMapper;
    private final AlbumMapper albumMapper;

    @Transactional
    public List<PersonDTO> searchPersonByName(String name) {
        List<Person> persons = personRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
        return persons.stream()
                .map(person -> personMapper.personToPersonDTO(person))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PersonDTO> searchPersonStageName(String name) {
        List<Person> persons = personRepository.findByStageNameContainingIgnoreCase(name);
        return persons.stream()
                .map(person -> personMapper.personToPersonDTO(person))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<AlbumDTO> searchAlbumByTitle(String title) {
        List<Album> albums = albumRepository.findByTitleContainingIgnoreCase(title);
        return albums.stream()
                .map(album -> albumMapper.albumToAlbumDTO(album))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<AlbumDTO> searchAlbumByGenre(String genre) {
        List<Album> albums = albumRepository.findByGenreContainingIgnoreCase(genre);
        return albums.stream()
                .map(album -> albumMapper.albumToAlbumDTO(album))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SongDTO> searchSongsByTitle(String title) {
        List<Song> songs = songRepository.findByTitleContainingIgnoreCase(title);
        return songs.stream()
                .map(song -> songMapper.songToSongDTO(song))
                .collect(Collectors.toList());
    }

}
