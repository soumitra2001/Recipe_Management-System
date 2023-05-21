package com.geekster.Recipe_Management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpInput {

    @NotBlank
    @Pattern(regexp = "[A-Z][A-Z\\Da-z]+")
    private String userName;

    @NotBlank
    @Email
    @Column(unique = true)
    private String userEmail;

    @NotEmpty
    @Pattern(regexp = "[A-Za-z\\D0-9]+")
    private String UserPassword;

    @Nullable
    @Pattern(regexp = "[0-9]{10,12}")
    private String userPhNo;

}
