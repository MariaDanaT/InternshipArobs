package com.example.musify.mapper;

import com.example.musify.dto.userdto.UserViewDTO;
import com.example.musify.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @BeforeAll
    public static void setup(){
        System.out.println("Setup was executed");
    }

    @BeforeEach
    public void init(){
        System.out.println("init was executed");
    }
    @Test
    @DisplayName("test for valid user view dto")
    public void givenValidUser_whenMappingUserDTO_thenReturnCorrectValidDTO() {
        Integer id = 1;
        String firstName = "Maria";
        String lastName = "Pop";
        String email = "mpop@gmail.com";
        String password = "1234";
        String country = "Romania";
        String role = "admin";
        Boolean deleted = false;

        User user = new User(id,firstName,lastName,email,password,country,role,deleted);

        UserMapper userMapper = new UserMapperImpl();
        UserViewDTO userViewDTO = userMapper.userDTOFromUser(user);

        assert(userViewDTO.getRole().equals(role));

    }

    @Test
    @DisplayName("test for null user view dto")
    public void givenNullUser_whenMappingUserDTO_thenReturnCorrectValidDTO() {

        UserMapper userMapper = new UserMapperImpl();
        UserViewDTO userViewDTO = userMapper.userDTOFromUser(null);

        assertNull (userViewDTO);

    }

}