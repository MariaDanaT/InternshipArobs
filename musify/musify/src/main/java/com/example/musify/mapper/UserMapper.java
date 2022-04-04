package com.example.musify.mapper;

import com.example.musify.dto.RegisterUserDTO;
import com.example.musify.dto.UserDTO;
import com.example.musify.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "firstName", expression = "java(userDTO.getFullName().split(\" \")[0])")
    @Mapping(target = "lastName", expression = "java(userDTO.getFullName().split(\" \")[1])")
    User userDTOToUser(UserDTO userDTO);

    @Mapping(target = "fullName", expression = "java(user.getFirstName() +\" \"+ user.getLastName())")
    UserDTO userToUserDTO(User user);

    @Mapping(target = "role", expression = "java(\"regular\")")
    User RegisterUserDTOTToUser(RegisterUserDTO registerUserDTO);
    @Mapping(target = "confirmPassword", expression = "java(user.getPassword())")
    RegisterUserDTO UserToRegisterUserDTO(User user);

}
