package com.example.project.webblog.Controllers;

import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Services.CommentService;
import com.example.project.webblog.Services.StoryService;
import com.example.project.webblog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;
    private StoryService storyService;
    private CommentService commentService;

    @Autowired
    public UserController(UserService userService, StoryService storyService, CommentService commentService) {
        this.userService = userService;
        this.storyService = storyService;
        this.commentService = commentService;
    }

    @RequestMapping("/")
    public String displayMain(Model model) {
        storyService.printStories(model);
        commentService.printComments(model);
        userService.printUsers(model);

        return "index";
    }

    @RequestMapping(value = "/registration")
    public String displayRegistration(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String userName, @RequestParam(required = false) String password, Model model) {
        return userService.registration(firstName, lastName, userName, password, model);
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam(required = false) String userName, @RequestParam(required = false) String password, Model model) {
        storyService.printStories(model);
        commentService.printComments(model);
        userService.printUsers(model);

        return userService.login(userName, password, model);
    }

    @RequestMapping("/home")
    public String displayHome(@RequestParam String userName, Model model) {
        storyService.printStories(model);
        commentService.printComments(model);
        userService.printUsers(model);

        if (userService.loggedInUserIsAdmin(userName, model)) {
            return "index_admin";
        }

        return "index_user";
    }

    @RequestMapping("/setadmin")
    public String displaySetAdmin(@RequestParam() String userName, Model model, @RequestParam() Optional<Long> userId) {
        userService.setAdmin(userName, model, userId);

        return "admin_setadmin";
    }
}