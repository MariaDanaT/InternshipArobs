package com.example.musify.service;

import com.example.musify.dto.playlistdto.PlaylistDTO;
import com.example.musify.entity.Playlist;
import com.example.musify.entity.User;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.PlaylistMapper;
import com.example.musify.repo.springdata.PlaylistRepository;
import com.example.musify.repo.springdata.UserRepository;
import com.example.musify.security.JwtUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final UserRepository userRepository;

    public PlaylistService(PlaylistRepository playlistRepository, PlaylistMapper playlistMapper, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.playlistMapper = playlistMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public PlaylistDTO create(PlaylistDTO playlistDTO, Integer idUser) {
        Playlist playlist = playlistMapper.playlistFromPlaylistDTO(playlistDTO);
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id=" + idUser);
        }
        User user = userOptional.get();
        playlist.setUser(user);
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
        Integer idUser = JwtUtils.getUserIdFromSession();
        if (!idUser.equals(playlist.getUser().getId())) {
            throw new UnauthorizedException("Only the owner can delete playlist!");
        }
        playlistRepository.delete(playlist);
    }
}
