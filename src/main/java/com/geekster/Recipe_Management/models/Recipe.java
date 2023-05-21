package com.geekster.Recipe_Management.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

    @NotBlank
    @Pattern(regexp = "[A-Za-z\\D0-9]+")
    @Column(unique = true)
    private String recipeName;

    @Pattern(regexp = "[A-Za-z\\D]+")
    private String recipeType;

    @NotBlank
    @Pattern(regexp = "[A-Za-z\\D0-9]+")
    private String ingredients;

    @NotBlank
    @Pattern(regexp = "[A-Za-z\\D0-9]+")
    private String instructions;

    @Min(value = 0L,message = "Like can not be negative!!")
    private Long recipeLike=0L;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "recipe")
    List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

}
