package com.example.musify.dto.userdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserViewDTO {
    private String email;
    private String password;

    public UserViewDTO(String mail, String password) {
        this.email = mail;
        this.password = password;
    }
}
