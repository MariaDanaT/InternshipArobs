package com.example.musify.controller;


import com.example.musify.dto.playlistdto.PlaylistDTO;
import com.example.musify.dto.userdto.RegisterUserDTO;
import com.example.musify.dto.userdto.UserLoginDTO;
import com.example.musify.dto.userdto.UserViewDTO;
import com.example.musify.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserViewDTO> users() {
        return userService.allUsers();
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PostMapping("/register")
    public Optional<UserViewDTO> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        UserViewDTO userViewDTO = userService.register(registerUserDTO);
        return Optional.of(userViewDTO);
    }

    @PutMapping("/delete/{id}")
    public Optional<UserViewDTO> inactivate(@RequestParam int id) {
        return userService.inactivate(id);
    }

    @PutMapping("/update")
    public Optional<UserViewDTO> update(@RequestBody UserViewDTO userViewDTO) {
        UserViewDTO userUpdated = userService.update(userViewDTO);
        return Optional.of(userUpdated);
    }

    @PostMapping("/follow/{playlistId}")
    public Optional<PlaylistDTO> followNewPlaylist(@RequestParam("playlistId") Integer playlistId) {
        PlaylistDTO playlistAdded = userService.followNewPlaylist(playlistId);
        return Optional.of(playlistAdded);
    }

    @GetMapping("/playlistHasOrFollow")
    public List<PlaylistDTO> getAllPlaylistsThatLoggedUserHasOrFollow() {
        return userService.getAllPlaylistsThatLoggedUserHasOrFollow();
    }
}
