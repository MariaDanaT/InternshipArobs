package com.example.musify.controller;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.service.BandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bands")
@AllArgsConstructor
public class BandController {
    private final BandService bandService;

    @GetMapping("/albums/{idBand}")
    public ResponseEntity<List<AlbumDTO>> loadAllAlbums(@PathVariable("idBand") Integer idBand) {
        List<AlbumDTO> albumsForBand = bandService.loadAllAlbums(idBand);
        return new ResponseEntity<>(albumsForBand, HttpStatus.OK);
    }
}
