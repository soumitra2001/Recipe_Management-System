package com.geekster.Recipe_Management.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"recipe","user"},allowSetters = true)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotBlank
    @Pattern(regexp = "[A-Za-z\\D0-9]+")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

}
