package com.geekster.Recipe_Management.services;

import com.geekster.Recipe_Management.models.Recipe;
import com.geekster.Recipe_Management.models.User;
import com.geekster.Recipe_Management.repositories.ICommentRepo;
import com.geekster.Recipe_Management.repositories.IRecipeRepo;
import com.geekster.Recipe_Management.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    IRecipeRepo recipeRepo;

    @Autowired
    AuthenticationService tokenService;

    @Autowired
    IUserRepo userRepo;
    public String saveMyRecipe(Recipe recipe, String email, String token) {
        if(!tokenService.authenticate(email,token)){
            return "Invalid user!!";
        }

        User user=userRepo.findByUserEmail(email);
        recipe.setUser(user);
        recipeRepo.save(recipe);

        return "Your recipe successfully posted!!";
    }

    public ResponseEntity<String> editRecipe(String name, Recipe recipe) {

        // First check any recipe is present or not of this name
        Recipe recipe1=recipeRepo.findByRecipeName(name);

        if(recipe1==null){
            return new ResponseEntity<>("No recipe exist with name "+name, HttpStatus.NOT_FOUND);
        }

        if(recipe.getIngredients()!=null)recipe1.setIngredients(recipe.getIngredients());
        if (recipe.getInstructions()!=null)recipe1.setInstructions(recipe.getInstructions());
        if(recipe.getRecipeLike()!=null)recipe1.setRecipeLike(recipe.getRecipeLike());

        return new ResponseEntity<>("Recipe successfully updated!!",HttpStatus.ACCEPTED);
    }

    public List<Recipe> getMyRecipes(Long id) {

        return recipeRepo.findByUser_UserId(id);
    }

    public String deleteRecipeByName(String name, String email, String token) {
        //Check if any recipe is present with this name or not
        Recipe recipe=recipeRepo.findByRecipeName(name);
        if(recipe==null)return "No recipe exist with the name "+name;

        //Authenticate the user
        if(!tokenService.authenticate(email,token)){
            return "User invalid!!";
        }

        //Get Authenticate User
        User user=userRepo.findByUserEmail(email);
        //Check if the recipe owner user and authenticate user is same then only we can delete the recipe

        //Get owner User
        User owner=recipe.getUser();
        if(!user.getUserId().equals(owner.getUserId())){
            return "Only owner can delete the recipe!!";
        }
        recipeRepo.delete(recipe);
        return "Recipe successfully deleted!!";
    }

    public List<Recipe> getAllRecipe() {
        return recipeRepo.findAll();
    }

    public Recipe getRecipeByName(String name) {
        return recipeRepo.getByRecipeName(name);
    }

    public List<Recipe> getRecipesByType(String type) {
        return recipeRepo.findByRecipeType(type);
    }


    @Transactional
    public HttpStatus likeRecipeById(Long id) {
        Recipe recipe=recipeRepo.findById(id).get();
        if(recipe.getRecipeName()==null)return HttpStatus.NOT_FOUND;

        recipe.setRecipeLike(recipe.getRecipeLike()+1);
        return HttpStatus.OK;
    }
}
