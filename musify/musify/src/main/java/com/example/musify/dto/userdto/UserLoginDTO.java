package com.example.musify.dto.userdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO {
    private String email;
    private String password;

    public UserLoginDTO(String mail, String password) {
        this.email = mail;
        this.password = password;
    }
}
