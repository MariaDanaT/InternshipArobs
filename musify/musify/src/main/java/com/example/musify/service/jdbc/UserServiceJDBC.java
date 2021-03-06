package com.example.musify.service.jdbc;

import com.example.musify.dto.userdto.RegisterUserDTO;
import com.example.musify.dto.userdto.UserViewDTO;
import com.example.musify.entity.User;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.UserMapper;
import com.example.musify.repo.jdbc.UserRepositoryJDBC;
import com.example.musify.security.JwtUtils;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceJDBC {
    @Autowired
    private UserRepositoryJDBC userRepository;

    @Autowired
    private UserMapper userMapper;

    public int addUserUsingFirstNameAndLastNameParameters(String firstName, String lastName) {
        return userRepository.addUserUsingFirstNameAndLastNameParameters(firstName, lastName);
    }

    public UserViewDTO findUserUsingEmailAndPassword(String email, String password) {
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
        User user = userRepository.addUser(userMapper.registerUserDTOToUser(registerUserDTO));
        return JwtUtils.generateToken(user.getId(), user.getEmail(), user.getRole());
    }

    @Transactional(readOnly = true)
    public List<UserViewDTO> getAll() {
//        List<UserViewDTO> userDTOS = new ArrayList<>();
        return userRepository.getAll().stream()
                .map(user -> userMapper.userDTOFromUser(user))
                .collect(Collectors.toList());
//        return userDTOS;
    }

}
