package com.example.musify.dto.userdto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserViewDTO {
    private int id;
    private String fullName;
    @NotNull
    private String email;
    private String country;
    private String role;
    private Boolean deleted;

    public UserViewDTO(int id, String fullName, String email, Boolean deleted) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.deleted = deleted;
    }
}
