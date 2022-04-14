package com.example.musify.controller;


import com.example.musify.dto.RegisterUserDTO;
import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserViewDTO;
import com.example.musify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


//    @PostMapping(value = "/login")
//    public UserDTO findUserUsingEmailAndPassword(@RequestBody UserDTO userDTO) {
//        return userService.findUserUsingEmailAndPassword(userDTO.getEmail(),userDTO.getPassword());
//    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody UserViewDTO userViewDTO) {
        String token = userService.login(userViewDTO.getEmail(), userViewDTO.getPassword());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping(value = "/allUsers", produces = "application/json")
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        String token = userService.addUser(registerUserDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
