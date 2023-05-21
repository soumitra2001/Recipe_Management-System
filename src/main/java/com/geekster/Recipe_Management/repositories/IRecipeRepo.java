package com.geekster.Recipe_Management.repositories;

import com.geekster.Recipe_Management.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRecipeRepo extends JpaRepository<Recipe,Long> {
    Recipe findByRecipeName(String name);

    List<Recipe> findByUser_UserId(Long id);

    Recipe getByRecipeName(String name);

    List<Recipe> findByRecipeType(String type);
}
