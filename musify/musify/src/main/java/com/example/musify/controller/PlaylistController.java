package com.example.musify.controller;

import com.example.musify.dto.playlistdto.PlaylistDTO;
import com.example.musify.security.JwtUtils;
import com.example.musify.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<PlaylistDTO> create(@RequestBody PlaylistDTO playlistDTO) {
        Integer idUser = JwtUtils.getUserIdFromSession();
        PlaylistDTO playlistCreated = playlistService.create(playlistDTO, idUser);
        return new ResponseEntity<>(playlistCreated, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PlaylistDTO> update(@RequestBody PlaylistDTO playlistDTO) {
        PlaylistDTO updatedPlaylist = playlistService.update(playlistDTO);
        return new ResponseEntity<>(updatedPlaylist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        playlistService.delete(id);
        return new ResponseEntity<>("Deleted with success!", HttpStatus.OK);
    }
}
