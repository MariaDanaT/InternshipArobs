package com.example.musify.service;

import com.example.musify.dto.UserDTO;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.UserMapper;
import com.example.musify.mapper.UserMapperImpl;
import com.example.musify.model.User;
import com.example.musify.repo.UserRepository;
import com.example.musify.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private UserMapper userMapper = new UserMapperImpl();

    public int addUserUsingFirstNameAndLastNameParameters(String firstName, String lastName) {
        return userRepository.addUserUsingFirstNameAndLastNameParameters(firstName, lastName);
    }

    public UserDTO findUserUsingEmailAndPassword(String email, String password) {
        return userMapper.userToUserDTO(userRepository.findUserUsingEmailAndPassword(email, password));
    }

    public String login(String email, String password) {
        User user = userRepository.findUserUsingEmailAndPassword(email, password);
        if (user.getId() == 0) {
            throw new UnauthorizedException("Email or password invalid!");
        }
        return JwtUtils.generateToken(user.getId(), user.getEmail(), user.getRole());

    }

    public List<UserDTO> getAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        userRepository.getAll().forEach(user -> userDTOS.add(userMapper.userToUserDTO(user)));
        return userDTOS;
    }

}
