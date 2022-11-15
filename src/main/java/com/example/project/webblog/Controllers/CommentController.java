package com.example.project.webblog.Controllers;

import com.example.project.webblog.Entities.Comment;
import com.example.project.webblog.Entities.Story;
import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Services.CommentService;
import com.example.project.webblog.Services.StoryService;
import com.example.project.webblog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class CommentController {

    private CommentService commentService;
    private UserService userService;
    private StoryService storyService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService, StoryService storyService) {
        this.commentService = commentService;
        this.userService = userService;
        this.storyService = storyService;
    }

    @RequestMapping("/admin/addcomment")
    public String addComment(@RequestParam(required = false) String userName, @RequestParam(required = false) String commentContent, Model model
    , @RequestParam(required = false) long storyId) {

        commentService.addComment(userName, commentContent, model, storyId, userService.getUserRepository(), storyService.getStoryRepository());

        storyService.printStory(model);

        model.addAttribute("comments", commentService.getCommentRepository().findAll());
        model.addAttribute("users", userService.getUserRepository().findAll());

        return "index_admin";
    }

    @RequestMapping("/admin/deletecomment")
    public String deleteComment(@RequestParam(required = false) String userName, Model model
            , @RequestParam(required = false) long commentId){

        commentService.deleteComment(userName, model, commentId, userService.getUserRepository());
        storyService.printStory(model);

        model.addAttribute("comments", commentService.getCommentRepository().findAll());
        model.addAttribute("users", userService.getUserRepository().findAll());


        return "index_admin";
    }
}
