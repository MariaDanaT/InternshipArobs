package com.example.musify.dto.userdto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
public class RegisterUserDTO {
    private int id;
    @NotBlank(message = "This field can't be blank!")
    private String firstName;
    @NotBlank(message = "This field can't be empty!")
    private String lastName;
    @NotBlank(message = "This field can't be empty!")
    private String email;
    @NotBlank(message = "This field can't be empty!")
    private String password;
    @NotBlank(message = "This field can't be empty!")
    private String confirmPassword;
    @NotBlank(message = "This field can't be empty!")
    private String country;

    public RegisterUserDTO(int id, String firstName, String lastName, String email, String password, String confirmPassword, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.country = country;
    }

}
