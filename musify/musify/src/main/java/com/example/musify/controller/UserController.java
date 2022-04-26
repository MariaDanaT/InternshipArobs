package com.example.musify.controller;


import com.example.musify.dto.userdto.RegisterUserDTO;
import com.example.musify.dto.userdto.UserLoginDTO;
import com.example.musify.dto.userdto.UserViewDTO;
import com.example.musify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserViewDTO> users() {
        return userService.allUsers();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = userService.login(userLoginDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserViewDTO> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        UserViewDTO userViewDTO = userService.register(registerUserDTO);
        return new ResponseEntity<>(userViewDTO, HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Optional<UserViewDTO>> inactivate(@RequestParam int id) {
        Optional<UserViewDTO> optional = userService.inactivate(id);
        return new ResponseEntity<>(optional, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserViewDTO> update(@RequestBody UserViewDTO userViewDTO) {
        UserViewDTO userUpdated = userService.update(userViewDTO);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }
}
