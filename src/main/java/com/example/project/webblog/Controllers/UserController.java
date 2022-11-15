package com.example.project.webblog.Controllers;

import com.example.project.webblog.Entities.Comment;
import com.example.project.webblog.Entities.Story;
import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Services.CommentService;
import com.example.project.webblog.Services.StoryService;
import com.example.project.webblog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        storyService.printStory(model);
        model.addAttribute("comments", commentService.getCommentRepository().findByOrderByCreationDateAsc());
        model.addAttribute("users", userService.getUserRepository().findAll());
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String displayRegistration(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String userName, @RequestParam(required = false) String password, Model model) {
        return userService.registration(firstName, lastName, userName, password, model);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(required = false) String userName, @RequestParam(required = false) String password, Model model) {
        storyService.printStory(model);
        model.addAttribute("comments", commentService.getCommentRepository().findByOrderByCreationDateAsc());
        model.addAttribute("users", userService.getUserRepository().findAll());
        model.addAttribute("loggedinuser",userService.getUserRepository().findUserByUserName(userName));
        return userService.login(userName, password,model);
    }
}