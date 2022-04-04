package com.example.musify.dto;


import javax.validation.constraints.NotBlank;

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
    private String originCountry;

    public RegisterUserDTO(int id, String firstName, String lastName, String email, String password, String confirmPassword, String originCountry) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.originCountry = originCountry;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
}
