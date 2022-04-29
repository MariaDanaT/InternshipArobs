package com.example.musify.controller;

import com.example.musify.dto.playlistdto.PlaylistDTO;
import com.example.musify.dto.playlistdto.PlaylistWithSongsTitleDTO;
import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.entity.Song;
import com.example.musify.security.JwtUtils;
import com.example.musify.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<PlaylistDTO> publicPlaylists() {
        return playlistService.publicPlaylists();
    }

    @PostMapping("/{idPlaylist}/{idSong}")
    public PlaylistWithSongsTitleDTO addSongToPlaylist(@RequestParam("idPlaylist") Integer idPlaylist, @RequestParam("idSong") Integer idSong) {
        PlaylistWithSongsTitleDTO playlistWithSongs = playlistService.addSongToPlaylist(idPlaylist, idSong);
        return playlistWithSongs;
    }

    @PostMapping("/{idPlaylist}/album/{idAlbum}")
    public ResponseEntity<List<SongDTO>> addAlbumToPlaylist(@RequestParam("idPlaylist") Integer idPlaylist, @RequestParam("idAlbum") Integer idAlbum) {
        List<SongDTO> songsFromPlaylist = playlistService.addAlbumToPlaylist(idPlaylist,idAlbum);
        return new ResponseEntity<>(songsFromPlaylist,HttpStatus.OK);
    }

    @GetMapping("/songs/{idPlaylist}")
    public ResponseEntity<List<SongDTO>> songsFromPlaylist(@RequestParam("idPlaylist") Integer idPlaylist) {
        List<SongDTO> songsFromPlaylist = playlistService.songsFromPlaylist(idPlaylist);
        return new ResponseEntity<>(songsFromPlaylist, HttpStatus.OK);
    }

    @DeleteMapping("/{idPlaylist}/{idSong}")
    public ResponseEntity<List<SongDTO>> removeSongFromPlaylist(@RequestParam("idPlaylist") Integer idPlaylist, @RequestParam("idSong") Integer idSong) {
        List<SongDTO> songsFromPlaylist = playlistService.removeSongFromPlaylist(idPlaylist, idSong);
        playlistService.reindexSongsForPlaylist(idPlaylist);
        return new ResponseEntity<>(songsFromPlaylist, HttpStatus.OK);
    }
}
