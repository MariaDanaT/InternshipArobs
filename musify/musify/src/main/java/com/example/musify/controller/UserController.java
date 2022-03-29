package com.example.musify.controller;


import com.example.musify.model.User;
import com.example.musify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(params = {"firstName", "lastName"}, produces = "application/json")
    public @ResponseBody
    int addUserUsingFirstNameAndLastNameParameters(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.addUserUsingFirstNameAndLastNameParameters(firstName, lastName);
    }

    @GetMapping(value = "/allUsers", produces = "application/json")
    public List<User> getAllUsers() {
        return userService.getAll();
    }
}
