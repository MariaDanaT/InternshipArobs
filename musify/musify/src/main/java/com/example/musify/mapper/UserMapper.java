package com.example.musify.mapper;

import com.example.musify.dto.RegisterUserDTO;
import com.example.musify.dto.UserDTO;
import com.example.musify.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "firstName", expression = "java(userDTO.getFullName().split(\" \")[0])")
    @Mapping(target = "lastName", expression = "java(userDTO.getFullName().split(\" \")[1])")
    User userDTOToUser(UserDTO userDTO);

    @Mapping(target = "fullName", expression = "java(user.getFirstName() +\" \"+ user.getLastName())")
    UserDTO userDTOFromUser(User user);

    @Mapping(target = "role", expression = "java(\"regular\")")
    User registerUserDTOTToUser(RegisterUserDTO registerUserDTO);
    @Mapping(target = "confirmPassword", expression = "java(user.getPassword())")
    RegisterUserDTO userToRegisterUserDTO(User user);

}
