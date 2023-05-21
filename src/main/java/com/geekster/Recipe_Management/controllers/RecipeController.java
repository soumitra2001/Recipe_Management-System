package com.geekster.Recipe_Management.controllers;

import com.geekster.Recipe_Management.models.Recipe;
import com.geekster.Recipe_Management.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipe(){
        List<Recipe> recipes = recipeService.getAllRecipe();

        return new ResponseEntity<>(recipes,recipes.isEmpty()? HttpStatus.NO_CONTENT:HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public Recipe getRecipeByName(@PathVariable String name){
        return recipeService.getRecipeByName(name);
    }

    @GetMapping("/type")
    public ResponseEntity<List<Recipe>> getRecipesByType(@PathVariable String type){
        List<Recipe> recipes = recipeService.getRecipesByType(type);

        return new ResponseEntity<>(recipes,recipes.isEmpty()? HttpStatus.NO_CONTENT:HttpStatus.OK);
    }
}
