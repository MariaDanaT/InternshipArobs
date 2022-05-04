package com.example.musify.service;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.entity.Band;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.repo.springdata.BandRepository;
import com.example.musify.service.utilcheck.Checker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BandService {
    private final BandRepository bandRepository;
    private final AlbumMapper albumMapper;

    @Transactional
    public List<AlbumDTO> loadAllAlbums(Integer idBand) {
        Optional<Band> bandOptional = bandRepository.findById(idBand);
        Band band = Checker.getBandIfExists(bandOptional, idBand);
        return band.getAlbums()
                .stream()
                .map(albumMapper::albumToAlbumDTO)
                .collect(Collectors.toList());
    }
}
