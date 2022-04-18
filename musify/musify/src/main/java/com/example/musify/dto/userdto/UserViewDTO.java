package com.example.musify.dto.userdto;

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
    private Boolean deleted;

    public UserDTO(int id, String fullName, String email, Boolean deleted) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.deleted = deleted;
    }
}
