package com.example.musify.service;

import com.example.musify.dto.artistdto.ArtistDTO;
import com.example.musify.entity.Artist;
import com.example.musify.mapper.ArtistMapper;
import com.example.musify.mapper.ArtistMapperImpl;
import com.example.musify.repo.springdata.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper = new ArtistMapperImpl();

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {

        this.artistRepository = artistRepository;
    }

    public List<ArtistDTO> getArtists() {
        List<ArtistDTO> artistDTOList = new ArrayList<>();
        artistRepository.findAll().forEach(x -> artistDTOList.add(artistMapper.artistToArtistDTO(x)));
        return artistDTOList;
    }

    public Optional<ArtistDTO> getArtistById(int id) {
        Optional<ArtistDTO> artistDTO = Optional.empty();
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            artistDTO = Optional.of(artistMapper.artistToArtistDTO(artist.get()));
        }
        return artistDTO;
    }

    public ArtistDTO addArtist(ArtistDTO artistDTO) {
        Artist artist = artistMapper.artistFromArtistDTO(artistDTO);
        return artistMapper.artistToArtistDTO(artistRepository.save(artist));
    }

    public ArtistDTO updateArtist(Integer id, ArtistDTO artistDTO) {
        Optional<Artist> optional = artistRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException("There is no artist with id=" + id);
        }
        Artist artist = optional.get();
        artist.setFirstName(artistDTO.getFirstName());
        artist.setLastName(artistDTO.getLastName());
        artist.setBirthday(artistDTO.getBirthday());
        artist.setActivityStartDate(artistDTO.getActivityStartDate());
        artist.setActivityEndDate(artistDTO.getActivityEndDate());
        artist.setType(artistDTO.getType());
        return artistMapper.artistToArtistDTO(artistRepository.save(artist));
    }

    public void deleteArtist(Integer id) {
        Optional<Artist> optional = artistRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException("There is no artist with id=" + id);
        }
        Artist artist = optional.get();
        artistRepository.delete(artist);
    }
}
