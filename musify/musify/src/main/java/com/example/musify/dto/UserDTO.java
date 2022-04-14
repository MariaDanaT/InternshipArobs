package com.example.musify.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String fullName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private Boolean deleted;

    public UserDTO(int id, String fullName, String email, String password, Boolean deleted) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.deleted = deleted;
    }
}
