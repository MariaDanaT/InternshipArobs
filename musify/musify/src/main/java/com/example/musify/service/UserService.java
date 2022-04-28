package com.example.musify.service;

import com.example.musify.dto.playlistdto.PlaylistDTO;
import com.example.musify.dto.userdto.RegisterUserDTO;
import com.example.musify.dto.userdto.UserLoginDTO;
import com.example.musify.dto.userdto.UserViewDTO;
import com.example.musify.entity.Playlist;
import com.example.musify.entity.User;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.PlaylistMapper;
import com.example.musify.mapper.UserMapper;
import com.example.musify.repo.springdata.PlaylistRepository;
import com.example.musify.repo.springdata.UserRepository;
import com.example.musify.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;


    @Transactional
    public UserViewDTO register(RegisterUserDTO registerUserDTO) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            throw new UnauthorizedException("Confirmed password doesn't match with password!");
        }
        byte[] bytes = registerUserDTO.getPassword().getBytes();
        String encoded = Hex.encodeHexString(bytes);
        registerUserDTO.setPassword(encoded);
        User user = userMapper.registerUserDTOToUser(registerUserDTO);
        return userMapper.userDTOFromUser(userRepository.save(user));
    }

    @Transactional
    public String login(UserLoginDTO userLoginDTO) {
        byte[] bytes = userLoginDTO.getPassword().getBytes();
        userLoginDTO.setPassword(Hex.encodeHexString(bytes));
        User user = userRepository.findUserByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        if (user.getId() == 0) {
            throw new UnauthorizedException("Email or password invalid!");
        }
        return JwtUtils.generateToken(user.getId(), user.getEmail(), user.getRole());
    }

    @Transactional
    public Optional<UserViewDTO> inactivate(int id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id =" + id);
        }
        optional.get().setDeleted(true);
        return Optional.ofNullable(userMapper.userDTOFromUser(optional.get()));
    }

    @Transactional
    public UserViewDTO update(UserViewDTO userViewDTO) {
        Optional<User> optional = userRepository.findById(userViewDTO.getId());
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id =" + userViewDTO.getId());
        }
        User updateUser = optional.get();
        List<String> fullName = List.of(userViewDTO.getFullName().split(" "));
        String firstName = fullName.get(0);
        String lastName = fullName.get(1);
        updateUser.setFirstName(firstName);
        updateUser.setLastName(lastName);
        updateUser.setEmail(userViewDTO.getEmail());
        updateUser.setCountry(userViewDTO.getCountry());
        updateUser.setRole(userViewDTO.getRole());
        updateUser.setDeleted(userViewDTO.getDeleted());

        return userMapper.userDTOFromUser(updateUser);
    }

    @Transactional(readOnly = true)
    public List<UserViewDTO> allUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userDTOFromUser)
                .collect(Collectors.toList());
    }

    @Transactional
    public PlaylistDTO followNewPlaylist(Integer playlistId) {
        Optional<Playlist> findPlaylistByIdOptional = playlistRepository.findById(playlistId);
        if (findPlaylistByIdOptional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with the id = " + playlistId);
        }
        Playlist playlist = findPlaylistByIdOptional.get();
        if (!playlist.getType().equals("public")) {
            throw new UnauthorizedException("This playlist can not be followed!");
        }
        Integer loggedUserId = JwtUtils.getUserIdFromSession();
        if (playlist.getUser().getId().equals(loggedUserId)) {
            throw new UnauthorizedException("This playlist is yours, you can not add it as followed!");
        }
        User user = userRepository.getById(loggedUserId);
        user.followNewPlaylist(playlist);
        return playlistMapper.playlistToPlaylistDTO(playlist);
    }

    @Transactional
    public List<PlaylistDTO> getAllPlaylistsThatLoggedUserHasOrFollow() {
        List<Playlist> playlists = new ArrayList<>();
        User user = userRepository.getById(JwtUtils.getUserIdFromSession());
        playlists.addAll(playlistRepository.findByUserIs(user));
        playlists.addAll(user.getFollowedPlaylists());
        return playlists.stream()
                .map(playlistMapper::playlistToPlaylistDTO)
                .collect(Collectors.toList());
    }
}
