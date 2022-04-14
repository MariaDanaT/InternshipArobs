package com.example.musify.service;

import com.example.musify.dto.RegisterUserDTO;
import com.example.musify.dto.UserDTO;
import com.example.musify.entity.User;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.UserMapper;
import com.example.musify.repo.UserRepository;
import com.example.musify.security.JwtUtils;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public int addUserUsingFirstNameAndLastNameParameters(String firstName, String lastName) {
        return userRepository.addUserUsingFirstNameAndLastNameParameters(firstName, lastName);
    }

    public UserDTO findUserUsingEmailAndPassword(String email, String password) {
        return userMapper.userDTOFromUser(userRepository.findUserUsingEmailAndPassword(email, password));
    }

    public String login(String email, String password) {
        byte[] bytes = password.getBytes();
        password = Hex.encodeHexString(bytes);
        User user = userRepository.findUserUsingEmailAndPassword(email, password);
        if (user.getId() == 0) {
            throw new UnauthorizedException("Email or password invalid!");
        }
        return JwtUtils.generateToken(user.getId(), user.getEmail(), user.getRole());

    }

    @Transactional
    public String addUser(RegisterUserDTO registerUserDTO) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            throw new UnauthorizedException("Confirmed password doesn't match with password!");
        }
        byte[] bytes = registerUserDTO.getPassword().getBytes();
        String encoded = Hex.encodeHexString(bytes);
        registerUserDTO.setPassword(encoded);
        User user = userRepository.addUser(userMapper.registerUserDTOTToUser(registerUserDTO));
        return JwtUtils.generateToken(user.getId(), user.getEmail(), user.getRole());
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
//        List<UserDTO> userDTOS = new ArrayList<>();
        return userRepository.getAll().stream()
                .map(user -> userMapper.userDTOFromUser(user))
                .collect(Collectors.toList());
//        return userDTOS;
    }

}
