package com.geekster.Recipe_Management.controllers;

import com.geekster.Recipe_Management.dto.SignInInput;
import com.geekster.Recipe_Management.dto.SignInOutput;
import com.geekster.Recipe_Management.dto.SignUpInput;
import com.geekster.Recipe_Management.dto.SignUpOutput;
import com.geekster.Recipe_Management.models.Comment;
import com.geekster.Recipe_Management.models.Recipe;
import com.geekster.Recipe_Management.models.User;
import com.geekster.Recipe_Management.services.AuthenticationService;
import com.geekster.Recipe_Management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService tokenService;

    @PostMapping("/signup")
    public SignUpOutput signUp(@Valid @RequestBody SignUpInput signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@Valid @RequestBody SignInInput signInDto){
        return userService.signIn(signInDto);
    }

    @DeleteMapping("/signout")
    @Transactional
    public ResponseEntity<String> signOut(@RequestParam String email , @RequestParam String token){
        HttpStatus status;
        String msg=null;

        if(tokenService.authenticate(email,token))
        {
            tokenService.deleteToken(token);
            msg = "logout Successful";
            status = HttpStatus.OK;

        }
        else
        {
            msg = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    @PostMapping("/recipe")
    public String addRecipe(@Valid @RequestBody Recipe recipe,@RequestParam String email,@RequestParam String token){
        return userService.saveRecipe(recipe,email,token);
    }

    // Any user can edit any recipe by  recipe name

    @PutMapping("recipe/{name}")
    public ResponseEntity<String> editRecipe(@PathVariable String name,@RequestBody Recipe recipe){
        return userService.editRecipe(name,recipe);
    }

    @GetMapping("/myrecipes/{id}")
    public ResponseEntity<List<Recipe>> findMyRecipes(@PathVariable Long id){
        List<Recipe> recipes=userService.findMyRecipes(id);

        return new ResponseEntity<>(recipes,recipes.isEmpty()?HttpStatus.NO_CONTENT:HttpStatus.OK);
    }

    @DeleteMapping("recipe/{name}")
    public String deleteRecipe(@PathVariable String name,@RequestParam String email,@RequestParam String token){
        return userService.deleteMyRecipe(name,email,token);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PostMapping("/recipe/comment/{recipeName}")
    public String addComment(@Valid @RequestBody Comment comment, @PathVariable String recipeName, @RequestParam String email, @RequestParam String token){
        return userService.commentOnRecipe(comment,recipeName,email,token);
    }

    //Only owner of the recipe can delete any comment by commentId
    @DeleteMapping("/recipe/{recipeId}/comment/{commentId}")
    public String deleteComment(@PathVariable String recipeName, @PathVariable Long commentId,@RequestParam String email,@RequestParam String token){
        return userService.deleteCommentById(recipeName,commentId,email,token);
    }

    @PutMapping("/recipe/{id}")
    public HttpStatus likeRecipe(@PathVariable Long id){
        return userService.likeRecipe(id);
    }

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

}
