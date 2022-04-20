package com.example.musify.controller;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.dto.songdto.SongWithAlbumDTO;
import com.example.musify.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/{id}")
    public Optional<AlbumDTO> getById(@RequestParam int id) {
        Optional<AlbumDTO> albumDTOOptional = albumService.getById(id);
        return albumDTOOptional;
    }

    @PostMapping
    public ResponseEntity<AlbumDTO> create(@RequestBody @Valid AlbumDTO albumDTO) {
        AlbumDTO albumToCreate = albumService.create(albumDTO);
        return new ResponseEntity<>(albumToCreate, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AlbumDTO> update(@RequestBody @Valid AlbumDTO albumDTO) {
        AlbumDTO updatedAlbum = albumService.update(albumDTO);
        return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
    }

    @PostMapping("/{idSong}/{idAlbum}")
    public ResponseEntity<List<SongWithAlbumDTO>> addSongToAlbum(@RequestParam("idSong") Integer idSong, @RequestParam("idAlbum") Integer idAlbum) {
        return new ResponseEntity<>(albumService.addSongToAlbum(idSong, idAlbum), HttpStatus.OK);
    }

}
