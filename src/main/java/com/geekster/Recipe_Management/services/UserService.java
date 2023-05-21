package com.geekster.Recipe_Management.services;

import com.geekster.Recipe_Management.dto.SignInInput;
import com.geekster.Recipe_Management.dto.SignInOutput;
import com.geekster.Recipe_Management.dto.SignUpInput;
import com.geekster.Recipe_Management.dto.SignUpOutput;
import com.geekster.Recipe_Management.models.AuthenticationToken;
import com.geekster.Recipe_Management.models.Comment;
import com.geekster.Recipe_Management.models.Recipe;
import com.geekster.Recipe_Management.models.User;
import com.geekster.Recipe_Management.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService tokenService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    CommentService commentService;


    public SignUpOutput signUp(SignUpInput signUpInput) {


        //check if user exists or not based on email
        User user = userRepo.findByUserEmail(signUpInput.getUserEmail());

        if(user != null)
        {
            throw new IllegalStateException("User already exists!!!!...sign in instead");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpInput.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        signUpInput.setUserPassword(encryptedPassword);

        user=new User(signUpInput.getUserName(),signUpInput.getUserEmail(),encryptedPassword,signUpInput.getUserPhNo());

        return new SignUpOutput(HttpStatus.OK,"User account created successfully");

    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;

    }

    public SignInOutput signIn(SignInInput signInDto) {

        //check if user exists or not based on email
        User user = userRepo.findByUserEmail(signInDto.getEmail());

        if(user == null)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(user.getUserPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        AuthenticationToken token = new AuthenticationToken(user);

        tokenService.saveToken(token);

        //set up output response

        return new SignInOutput(HttpStatus.OK, token.getToken());


    }

    public String saveRecipe(Recipe recipe, String email, String token) {
        return recipeService.saveMyRecipe(recipe,email,token);
    }

    public ResponseEntity<String> editRecipe(String name, Recipe recipe) {
        return recipeService.editRecipe(name,recipe);
    }

    public List<Recipe> findMyRecipes(Long id) {
        return recipeService.getMyRecipes(id);
    }

    public String deleteMyRecipe(String name, String email, String token) {
        return recipeService.deleteRecipeByName(name,email,token);
    }

    public User findUserById(Long id){
        return userRepo.findById(id).get();
    }

    public String commentOnRecipe(Comment comment, String recipeName, String email, String token) {
        //Authenticate the user
        if(!tokenService.authenticate(email,token)){
            return "User invalid!!";
        }

        User user=userRepo.findByUserEmail(email);
        return commentService.saveCommentForRecipe(comment,recipeName,user);
    }

    @Transactional
    public String deleteCommentById(String recipeName, Long commentId, String email, String token) {
        //Authenticate the user
        if(!tokenService.authenticate(email,token)){
            return "User invalid!!";
        }
        User user=userRepo.findByUserEmail(email);

        //Check owner user and given user is same or not
        User owner=recipeService.getRecipeByName(recipeName).getUser();
        if(!user.getUserId().equals(owner.getUserId())){
            return "Only owner can delete any comments!!";
        }
        return commentService.deleteCommentById(commentId);
    }

    public HttpStatus likeRecipe(Long id) {
        return recipeService.likeRecipeById(id);
    }

}
