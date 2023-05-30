package com.geekster.Recipe_Management.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Pattern(regexp = "[A-Z][A-Z\\Da-z]+")
    private String userName;

    @NotBlank
    @Email
    @Column(unique = true)
    private String userEmail;

    @NotEmpty
    @Pattern(regexp = "[A-Za-z\\D0-9]+")
    private String userPassword;

    @Nullable
    @Pattern(regexp = "[0-9]{10,12}")
    private String userPhNo;

    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "user")
    @JsonManagedReference
    private List<Recipe> recipes;

    public User(String userName, String userEmail, String encryptedPassword, String userPhNo) {
        this.userName=userName;
        this.userEmail=userEmail;
        this.userPassword=encryptedPassword;
        this.userPhNo=userPhNo;
    }

}
