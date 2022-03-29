package com.example.musify.service;

import com.example.musify.model.User;
import com.example.musify.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public int addUserUsingFirstNameAndLastNameParameters(String firstName, String lastName) {
        return userRepository.addUserUsingFirstNameAndLastNameParameters(firstName, lastName);
    }

    public List<User> getAll(){
        return userRepository.getAll();
    }

}
