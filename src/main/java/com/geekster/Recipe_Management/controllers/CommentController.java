package com.geekster.Recipe_Management.controllers;

import com.geekster.Recipe_Management.models.Comment;
import com.geekster.Recipe_Management.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    //Get all comments for a recipe
    @GetMapping("/{recipeName}")
    public List<Comment> getRecipeComments(@PathVariable String recipeName){
        return commentService.getRecipeComments(recipeName);
    }
}
