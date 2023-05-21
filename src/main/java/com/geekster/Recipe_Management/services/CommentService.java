package com.geekster.Recipe_Management.services;

import com.geekster.Recipe_Management.models.Comment;
import com.geekster.Recipe_Management.models.Recipe;
import com.geekster.Recipe_Management.models.User;
import com.geekster.Recipe_Management.repositories.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;

    @Autowired
    RecipeService recipeService;

    public List<Comment> getRecipeComments(String recipeName) {
        Recipe recipe=recipeService.getRecipeByName(recipeName);
        return commentRepo.findByRecipe(recipe);
    }

    @Transactional
    public String saveCommentForRecipe(Comment comment, String recipeName, User user) {
        //Find the recipe with this name
        Recipe recipe=recipeService.getRecipeByName(recipeName);
        if(recipe==null)return "No recipe exist with the name "+recipeName;

        //Set the commented user and recipe
        comment.setUser(user);
        comment.setRecipe(recipe);
        commentRepo.save(comment);
        return "You post a comment on recipe "+recipeName;
    }

    public String deleteCommentById(Long commentId) {
        commentRepo.deleteById(commentId);
        return "Comment successfully deleted!!!";
    }

}
