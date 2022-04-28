package com.example.musify.service;

import com.example.musify.dto.playlistdto.PlaylistDTO;
import com.example.musify.dto.playlistdto.PlaylistWithSongsTitleDTO;
import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.entity.Playlist;
import com.example.musify.entity.PlaylistsSongs;
import com.example.musify.entity.Song;
import com.example.musify.entity.User;
import com.example.musify.exception.DuplicateException;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.PlaylistMapper;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.springdata.PlaylistRepository;
import com.example.musify.repo.springdata.PlaylistsSongsRepository;
import com.example.musify.repo.springdata.SongRepository;
import com.example.musify.repo.springdata.UserRepository;
import com.example.musify.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final UserRepository userRepository;
    private final PlaylistsSongsRepository playlistsSongsRepository;
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Transactional
    public PlaylistDTO create(PlaylistDTO playlistDTO, Integer idUser) {
        Playlist playlist = playlistMapper.playlistFromPlaylistDTO(playlistDTO);
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id=" + idUser);
        }
        User user = userOptional.get();
        playlist.setUser(user);
        user.addPlaylistToOwnerUser(playlist);
        return playlistMapper.playlistToPlaylistDTO(playlistRepository.save(playlist));
    }

    @Transactional
    public PlaylistDTO update(PlaylistDTO playlistDTO) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistDTO.getId());
        if (playlistOptional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + playlistDTO.getId());
        }
        Integer idUser = JwtUtils.getUserIdFromSession();
        Playlist playlist = playlistOptional.get();
        if (!idUser.equals(playlist.getUser().getId())) {
            throw new UnauthorizedException("Only the owner can edit playlist!");
        }
        playlist.setType(playlistDTO.getType());
        playlist.setCreatedDate(playlistDTO.getCreatedDate());
        playlist.setLastUpdateDate(playlistDTO.getLastUpdateDate());

        return playlistMapper.playlistToPlaylistDTO(playlist);
    }

    @Transactional
    public void delete(Integer id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id=" + id);
        }
        Playlist playlist = optionalPlaylist.get();
        User loggedUser = userRepository.getById(JwtUtils.getUserIdFromSession());
        if (!loggedUser.getId().equals(playlist.getUser().getId())) {
            throw new UnauthorizedException("Only the owner can delete playlist!");
        }
        loggedUser.removePlaylistFromOwnerUser(playlist);
        playlist.getUsers()
                .forEach(user -> user.removePlaylistsFromFollowedPlaylistsTable(playlist));
        playlistRepository.delete(playlist);
    }

    @Transactional(readOnly = true)
    public List<PlaylistDTO> publicPlaylists() {
        return playlistRepository.findByTypeLike("public")
                .stream()
                .map(playlistMapper::playlistToPlaylistDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PlaylistWithSongsTitleDTO addSongToPlaylist(Integer idPlaylist, Integer idSong) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(idPlaylist);
        if (playlistOptional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + idPlaylist);
        }
        Playlist playlist = playlistOptional.get();
        if (!playlist.getUser().getId().equals(JwtUtils.getUserIdFromSession())) {
            throw new UnauthorizedException("Only the owner can add song to the playlist!");
        }
        Optional<Song> songOptional = songRepository.findById(idSong);
        if (songOptional.isEmpty()) {
            throw new ResourceNotFoundException("There is no song with id = " + idSong);
        }
        PlaylistsSongs playlistsSongsToAdd = createPlaylistsSongsWithGivenPlaylistAndSong(playlist, songOptional.get());
        List<Integer> idSongsForPlaylist = new ArrayList<>();
        songRepository.findAll(playlist.getId()).forEach(song -> idSongsForPlaylist.add(song.getId()));
        if (idSongsForPlaylist.contains(idSong)) {
            throw new DuplicateException("This song was added before!");
        }
        playlistsSongsRepository.save(playlistsSongsToAdd);
        idSongsForPlaylist.add(idSong);
        List<String> songsTitle = new ArrayList<>();
        idSongsForPlaylist.forEach(songId -> songsTitle.add(songRepository.getById(songId).getTitle()));
        return playlistMapper.playlistWithTheirSongsTitle(playlist.getId(), songsTitle);
    }

    @Transactional
    public List<SongDTO> songsFromPlaylist(Integer idPlaylist) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(idPlaylist);
        if (playlistOptional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + idPlaylist);
        }
        if (!playlistOptional.get().getType().equals("public")) {
            throw new UnauthorizedException("Can not show songs!");
        }
        List<Song> songsFromPlaylist = songRepository.findAll(idPlaylist);
        return songsFromPlaylist.stream()
                .map(songMapper::songToSongDTO)
                .collect(Collectors.toList());

    }

    @Transactional
    public List<SongDTO> removeSongFromPlaylist(Integer idPlaylist, Integer idSong) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(idPlaylist);
        if (playlistOptional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + idPlaylist);
        }
        Playlist playlist = playlistOptional.get();
        Optional<Song> songOptional = songRepository.findAll(idPlaylist, idSong);
        if (songOptional.isEmpty()) {
            throw new ResourceNotFoundException("This song doesn't exist in the playlist!");
        }
        Song song = songOptional.get();
        PlaylistsSongs playlistSong = playlistsSongsRepository.findBySongFromPlaylistAndPlaylist(song, playlist);
        playlistsSongsRepository.delete(playlistSong);
        playlist.setLastUpdateDate(Date.valueOf(java.time.LocalDate.now()));
        return songRepository.findAll(idPlaylist)
                .stream()
                .map(songMapper::songToSongDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void reindexSongsForPlaylist(Integer idPlaylist) {
        AtomicReference<Integer> count = new AtomicReference<>(0);
        Playlist playlist = playlistRepository.getById(idPlaylist);
        Set<PlaylistsSongs> playlistsSongsSet = playlist.getPlaylistsSongs();
        for (PlaylistsSongs playlistSong : playlistsSongsSet
        ) {
            if (playlistSong.getPlaylist().getId().equals(idPlaylist)) {
                count.set(count.get() + 1);
                playlistSong.setOrderNumber(count.get());
            }
        }
        playlist.setPlaylistsSongs(playlistsSongsSet);
    }

    private PlaylistsSongs createPlaylistsSongsWithGivenPlaylistAndSong(Playlist playlist, Song song) {
        PlaylistsSongs playlistsSongs = new PlaylistsSongs();

        playlist.setLastUpdateDate(Date.valueOf(java.time.LocalDate.now()));

        playlistsSongs.setPlaylist(playlist);
        playlistsSongs.setSongFromPlaylist(song);
        int count = Math.toIntExact(playlistsSongsRepository.countByPlaylist(playlist));
        playlistsSongs.setOrderNumber(count + 1);
        return playlistsSongs;
    }

}
