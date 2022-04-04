package com.example.musify.service;

import com.example.musify.dto.RegisterUserDTO;
import com.example.musify.dto.UserDTO;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.UserMapper;
import com.example.musify.mapper.UserMapperImpl;
import com.example.musify.model.User;
import com.example.musify.repo.UserRepository;
import com.example.musify.security.JwtUtils;
import org.apache.commons.codec.binary.Hex;
import org.mapstruct.control.MappingControl;
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
        byte[] bytes = password.getBytes();
        password = Hex.encodeHexString(bytes);
        User user = userRepository.findUserUsingEmailAndPassword(email, password);
        if (user.getId() == 0) {
            throw new UnauthorizedException("Email or password invalid!");
        }
        return JwtUtils.generateToken(user.getId(), user.getEmail(), user.getRole());

    }

    public String addUser(RegisterUserDTO registerUserDTO) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            throw new UnauthorizedException("Confirmed password doesn't match with password!");
        }
        byte[] bytes = registerUserDTO.getPassword().getBytes();
        String encoded = Hex.encodeHexString(bytes);
        registerUserDTO.setPassword(encoded);
        User user = userRepository.addUser(userMapper.RegisterUserDTOTToUser(registerUserDTO));
        return JwtUtils.generateToken(user.getId(), user.getEmail(), user.getRole());
    }

    public List<UserDTO> getAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        userRepository.getAll().forEach(user -> userDTOS.add(userMapper.userToUserDTO(user)));
        return userDTOS;
    }

}
