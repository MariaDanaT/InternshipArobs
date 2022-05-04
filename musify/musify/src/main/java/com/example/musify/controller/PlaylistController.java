package com.example.musify.controller;

import com.example.musify.dto.playlistdto.PlaylistDTO;
import com.example.musify.dto.playlistdto.PlaylistWithSongsTitleDTO;
import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.security.JwtUtils;
import com.example.musify.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
@AllArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping
    public Optional<PlaylistDTO> create(@RequestBody PlaylistDTO playlistDTO) {
        Integer idUser = JwtUtils.getUserIdFromSession();
        PlaylistDTO playlistCreated = playlistService.create(playlistDTO, idUser);
        return Optional.of(playlistCreated);
    }

    @PutMapping
    public Optional<PlaylistDTO> update(@RequestBody PlaylistDTO playlistDTO) {
        PlaylistDTO updatedPlaylist = playlistService.update(playlistDTO);
        return Optional.ofNullable(updatedPlaylist);
    }

    @DeleteMapping("/{id}")
    public Optional<PlaylistDTO> delete(@PathVariable("id") Integer idPlaylist) {
        return Optional.of(playlistService.delete(idPlaylist));
    }

    @GetMapping
    public List<PlaylistDTO> publicPlaylists() {
        return playlistService.publicPlaylists();
    }

    @PostMapping("/{idPlaylist}/{idSong}")
    public Optional<PlaylistWithSongsTitleDTO> addSongToPlaylist(@RequestParam("idPlaylist") Integer idPlaylist, @RequestParam("idSong") Integer idSong) {
        PlaylistWithSongsTitleDTO playlistWithSongs = playlistService.addSongToPlaylist(idPlaylist, idSong);
        return Optional.of(playlistWithSongs);
    }

    @PostMapping("/{idPlaylist}/album/{idAlbum}")
    public List<SongDTO> addAlbumToPlaylist(@RequestParam("idPlaylist") Integer idPlaylist, @RequestParam("idAlbum") Integer idAlbum) {
        return playlistService.addAlbumToPlaylist(idPlaylist, idAlbum);
    }

    @GetMapping("/songs/{idPlaylist}")
    public List<SongDTO> songsFromPlaylist(@RequestParam("idPlaylist") Integer idPlaylist) {
        return playlistService.songsFromPlaylist(idPlaylist);
    }

    @DeleteMapping("/{idPlaylist}/{idSong}")
    public List<SongDTO> removeSongFromPlaylist(@RequestParam("idPlaylist") Integer idPlaylist, @RequestParam("idSong") Integer idSong) {
        List<SongDTO> songsFromPlaylist = playlistService.removeSongFromPlaylist(idPlaylist, idSong);
        playlistService.reindexSongsForPlaylist(idPlaylist);
        return songsFromPlaylist;
    }

    @PutMapping("/changeOrderSongInPlaylist/{idPlaylist}/{idSong}/{newPosition}")
    public List<SongDTO> changeOrderSongInPlaylist(@PathVariable("idPlaylist") Integer idPlaylist, @PathVariable("idSong") Integer idSong, @PathVariable("newPosition") Integer newPosition) {
        playlistService.changeOrderSongInPlaylist(idPlaylist, idSong, newPosition);
        return playlistService.songsFromPlaylist(idPlaylist);
    }
}
