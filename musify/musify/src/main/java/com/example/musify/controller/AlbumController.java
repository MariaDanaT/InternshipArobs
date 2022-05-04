package com.example.musify.controller;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.dto.songdto.SongWithAlbumDTO;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.service.AlbumService;
import com.example.musify.service.utilcheck.Checker;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AlbumDTO create(@RequestBody @Valid AlbumDTO albumDTO) {
        if (!Checker.isAdmin())
            throw new UnauthorizedException("Only admins can add albums!");
        AlbumDTO albumToCreate = albumService.create(albumDTO);
        return albumToCreate;
    }

    @PutMapping
    public AlbumDTO update(@RequestBody @Valid AlbumDTO albumDTO) {
        AlbumDTO updatedAlbum = albumService.update(albumDTO);
        return updatedAlbum;
    }

    @PostMapping("/{idSong}/{idAlbum}")
    public List<SongWithAlbumDTO> addSongToAlbum(@RequestParam("idSong") Integer idSong, @RequestParam("idAlbum") Integer idAlbum) {
        return albumService.addSongToAlbum(idSong, idAlbum);
    }

    @GetMapping("/songs/{idAlbum}")
    public List<SongDTO> allSongsForAlbum(@PathVariable("idAlbum") Integer idAlbum) {
        return albumService.allSongsForAlbum(idAlbum);
    }
}
