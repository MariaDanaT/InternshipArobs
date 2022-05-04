package com.example.musify.service;

import com.example.musify.dto.playlistdto.PlaylistDTO;
import com.example.musify.dto.playlistdto.PlaylistWithSongsTitleDTO;
import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.entity.*;
import com.example.musify.exception.DuplicateException;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.PlaylistMapper;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.springdata.*;
import com.example.musify.security.JwtUtils;
import com.example.musify.service.utilcheck.Checker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;
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
    private final AlbumRepository albumRepository;

    @Transactional
    public PlaylistDTO create(PlaylistDTO playlistDTO, Integer idUser) {
        Playlist playlist = playlistMapper.playlistFromPlaylistDTO(playlistDTO);
        Optional<User> userOptional = userRepository.findById(idUser);
        User user = Checker.getUserIfExists(userOptional, idUser);
        playlist.setUser(user);
        user.addPlaylistToOwnerUser(playlist);
        return playlistMapper.playlistToPlaylistDTO(playlistRepository.save(playlist));
    }

    @Transactional
    public PlaylistDTO update(PlaylistDTO playlistDTO) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistDTO.getId());
        Integer idUser = JwtUtils.getUserIdFromSession();
        Playlist playlist = Checker.getPlaylistIfExists(playlistOptional, playlistDTO.getId());
        if (!idUser.equals(playlist.getUser().getId())) {
            throw new UnauthorizedException("Only the owner can edit playlist!");
        }
        playlistMapper.mergePlaylistAndPlaylistDTO(playlist,playlistDTO);
        return playlistMapper.playlistToPlaylistDTO(playlist);
    }

    @Transactional
    public PlaylistDTO delete(Integer idPlaylist) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(idPlaylist);
        Playlist playlist = Checker.getPlaylistIfExists(optionalPlaylist, idPlaylist);
        User loggedUser = userRepository.getById(JwtUtils.getUserIdFromSession());
        if (!loggedUser.getId().equals(playlist.getUser().getId())) {
            throw new UnauthorizedException("Only the owner can delete playlist!");
        }
        loggedUser.removePlaylistFromOwnerUser(playlist);
        playlist.getUsers()
                .forEach(user -> user.removePlaylistsFromFollowedPlaylistsTable(playlist));
        playlistRepository.delete(playlist);
        return playlistMapper.playlistToPlaylistDTO(playlist);
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
        Playlist playlist = Checker.getPlaylistIfExists(playlistOptional, idPlaylist);
        if (!playlist.getUser().getId().equals(JwtUtils.getUserIdFromSession())) {
            throw new UnauthorizedException("Only the owner can add song to the playlist!");
        }
        Optional<Song> songOptional = songRepository.findById(idSong);
        PlaylistsSongs playlistsSongs = new PlaylistsSongs();
        Song song = Checker.getSongIfExists(songOptional, idSong);
        PlaylistsSongs playlistsSongsToAdd = createPlaylistsSongsWithGivenPlaylistAndSong(playlist, song, playlistsSongs);
        List<Integer> idSongsForPlaylist = new ArrayList<>();
        songRepository.findAll(playlist.getId()).forEach(s -> idSongsForPlaylist.add(s.getId()));
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
    public List<SongDTO> addAlbumToPlaylist(Integer idPlaylist, Integer idAlbum) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(idPlaylist);
        Playlist playlist = Checker.getPlaylistIfExists(playlistOptional, idPlaylist);
        if (!playlist.getUser().getId().equals(JwtUtils.getUserIdFromSession())) {
            throw new UnauthorizedException("Only the owner can add song to the playlist!");
        }
        Optional<Album> albumOptional = albumRepository.findById(idAlbum);
        Album album = Checker.getAlbumIfExists(albumOptional, idAlbum);
        List<Song> songsExistsInPlaylist = songRepository.findAll(idPlaylist);
        List<Song> songsFromAlbum = album.getSongs().stream()
                .sorted(Comparator.comparing(Song::getId))
                .collect(Collectors.toList());
        songsFromAlbum.forEach(song -> {
            if (!songsExistsInPlaylist.contains(song)) {
                PlaylistsSongs playlistsSongs = new PlaylistsSongs();
                PlaylistsSongs playlistsSongsToAdd = createPlaylistsSongsWithGivenPlaylistAndSong(playlist, song, playlistsSongs);
                playlistsSongsRepository.save(playlistsSongsToAdd);
                songsExistsInPlaylist.add(song);
            }
        });
        return songsExistsInPlaylist
                .stream()
                .map(songMapper::songToSongDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SongDTO> songsFromPlaylist(Integer idPlaylist) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(idPlaylist);
        Playlist playlist = Checker.getPlaylistIfExists(playlistOptional, idPlaylist);
        if (!playlist.getType().equals("public")) {
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
        Playlist playlist = Checker.getPlaylistIfExists(playlistOptional, idPlaylist);
        Optional<Song> songOptional = songRepository.findAll(idPlaylist, idSong);

        Song song = Checker.getSongIfExistsInPlaylist(songOptional);
        PlaylistsSongs playlistSong = playlistsSongsRepository.findBySongFromPlaylistAndPlaylist(song, playlist);
        playlistsSongsRepository.delete(playlistSong);
        playlist.setLastUpdateDate(Date.valueOf(java.time.LocalDate.now()));
        return songRepository.findAll(idPlaylist)
                .stream()
                .map(songMapper::songToSongDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void changeOrderSongInPlaylist(Integer idPlaylist, Integer idSong, Integer newPosition) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(idPlaylist);
        Optional<Song> songOptional = songRepository.findAll(idPlaylist, idSong);
        Song song = Checker.getSongIfExistsInPlaylist(songOptional);
        Playlist playlist = Checker.getPlaylistIfExists(playlistOptional, idPlaylist);
        List<Song> songsFromPlaylist = playlistsSongsRepository.findAll(idPlaylist);
        int oldPosition = songsFromPlaylist.indexOf(song);
        LinkedList<Song> songsLinkedList = new LinkedList<>(songsFromPlaylist);
        if (oldPosition < newPosition) {
            songsLinkedList.add(newPosition, song);
            songsLinkedList.remove(oldPosition);
        } else {
            songsLinkedList.add(newPosition - 1, song);
            songsLinkedList.remove(oldPosition + 1);
        }
        List<PlaylistsSongs> psList = playlist.getPlaylistsSongs()
                .stream()
                .sorted(Comparator.comparing(PlaylistsSongs::getOrderNumber))
                .collect(Collectors.toList());
        for (PlaylistsSongs ps : psList
        ) {
            ps.setSongFromPlaylist(songsLinkedList.peek());
            songsLinkedList.remove();
        }
    }


    @Transactional
    public void reindexSongsForPlaylist(Integer idPlaylist) {
        AtomicReference<Integer> count = new AtomicReference<>(0);
        Playlist playlist = playlistRepository.getById(idPlaylist);
        List<PlaylistsSongs> playlistsSongsList = playlist.getPlaylistsSongs()
                .stream()
                .sorted(Comparator.comparing(PlaylistsSongs::getId))
                .collect(Collectors.toList());
        for (PlaylistsSongs playlistSong : playlistsSongsList
        ) {
            if (playlistSong.getPlaylist().getId().equals(idPlaylist)) {
                count.set(count.get() + 1);
                playlistSong.setOrderNumber(count.get());
            }
        }
        playlist.setPlaylistsSongs(new HashSet<>(playlistsSongsList));
    }

    private PlaylistsSongs createPlaylistsSongsWithGivenPlaylistAndSong(Playlist playlist, Song song, PlaylistsSongs playlistsSongs) {
        playlist.setLastUpdateDate(Date.valueOf(java.time.LocalDate.now()));
        playlistsSongs.setPlaylist(playlist);
        playlistsSongs.setSongFromPlaylist(song);
        int count = Math.toIntExact(playlistsSongsRepository.countByPlaylist(playlist));
        playlistsSongs.setOrderNumber(count + 1);
        return playlistsSongs;
    }

}
