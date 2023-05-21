<h1 align="center">🍜🍛🍝 Recipe Management System 🍚🍔</h1>

This repository contains the source code for a Recipe Management System application. The application allows users to manage and share recipes, add comments, and authenticate themselves using an authentication token. It provides various controllers for handling different functionalities.


## Frameworks and Language used

[![JAVA Docs](https://img.shields.io/badge/JAVA-v20.0.1-blue.svg)](https://docs.oracle.com/en/java/)
[![Spring_Boot](https://img.shields.io/badge/Spring_Boot-v3.0.6-yellow.svg)](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)


## Required Dependencies

To run this project, you will need to add the following dependencies to your pom.xml file

`Spring Web`, `Spring Boot Dev Tool`, `Lombok`, `Validation`, `Spring Data JPA`, `MySql Driver`, `Swagger-UI`


## Entities
The following entities are used in the application:

* #### `User`: Represents a user of the application.
* #### `Recipe`: Represents a recipe posted by a user.
* #### `Comment`:  Represents a comment made by a user on a recipe.
* #### `AuthenticationToken`: Represents an authentication token used to authenticate users.

## Controllers
The application contains several controllers to handle different operations and manage the flow of data. The controllers and their associated methods are as follows:

### UserController
* #### `signUp`: Allows a new user to sign up by providing their details.
* #### `signIn`: Authenticates a user by validating their credentials.
* #### `signOut`: Logs out the currently authenticated user.
* #### `addRecipe`: Enables a user to add a new recipe.
* #### `editRecipe`: Allows a user to update an existing recipe.
* #### `findMyRecipes`: Retrieves all recipes posted by a specific user.
* #### `deleteRecipe`: Deletes a recipe, only if the user is the owner.
* #### `addComment`: Allows a user to add a comment to a recipe.
* #### `deleteComment`: Allows the owner of a recipe to delete any comments associated with that recipe.
* #### `likeRecipe`: Enables any user to like a recipe.
* #### `getAllUsers`: Retrieves all users using the application.

### RecipeController
* #### `getAllRecipe`: Retrieves all recipes available in the system.
* #### `getRecipeByName`: Retrieves a recipe by its name.

### CommentController
* #### `getRecipeComments`: Retrieves all comments associated with a recipe.

## DataBase Design
#### Used MySQL DataBase

```
    
    create table authentication_token (
        token_id bigint not null auto_increment,
        token varchar(255),
        token_creation_date date,
        fk_user_id bigint not null,
        primary key (token_id)
    )
    create table comment (
        comment_id bigint not null auto_increment,
        fk_user_id bigint not null,
        primary key (comment_id)
    )
    create table recipe (
        recipe_id bigint not null auto_increment,
        recipe_name varchar(255) not null,
        recipe_type varchar(255) not null,
        ingredients varchar(255) not null,
        instructions varchar(255) not null,
        recipeLike bigint,
        fk_user_id bigint not null,
        primary key (recipe_id)
    )
    create table user (
        user_id bigint not null auto_increment,
        email varchar(255) not null,
        user_name varchar(255) not null,
        password varchar(255) not null,
        phone_number varchar(255),
        primary key (user_id)
    )

```

## Data Structure used in project :
- List

## Contribution
Contributions to the Recipe Management System application are welcome. If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## Project Summary :

````
The Recipe Management System is a web application that allows users to manage and share recipes😋. Users can sign up, sign in, and sign out, and have the ability to add, edit, and delete their recipes. They can also add comments to recipes and like recipes posted by other users. The application provides authentication using an authentication token and includes controllers for user management, recipe management, and comment handling.
 
````


