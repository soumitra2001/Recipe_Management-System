package com.geekster.Recipe_Management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInInput {

    @NotBlank(message = "Email cannot be blank")
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Pattern(regexp = "[A-Za-z\\D0-9]+")
    private String password;

}
