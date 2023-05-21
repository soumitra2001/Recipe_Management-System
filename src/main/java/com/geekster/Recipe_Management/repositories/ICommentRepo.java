package com.geekster.Recipe_Management.repositories;

import com.geekster.Recipe_Management.models.Comment;
import com.geekster.Recipe_Management.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByRecipe(Recipe recipe);
}
