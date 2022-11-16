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
        storyService.printStory(model);
        model.addAttribute("comments", commentService.getCommentRepository().findByOrderByCreationDateAsc());
        model.addAttribute("users", userService.getUserRepository().findAll());
        return "index";
    }

    @RequestMapping(value = "/registration")
    public String displayRegistration(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String userName, @RequestParam(required = false) String password, Model model) {
        return userService.registration(firstName, lastName, userName, password, model);
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam(required = false) String userName, @RequestParam(required = false) String password, Model model) {
        storyService.printStory(model);
        model.addAttribute("comments", commentService.getCommentRepository().findByOrderByCreationDateAsc());
        model.addAttribute("users", userService.getUserRepository().findAll());
        model.addAttribute("loggedinuser", userService.getUserRepository().findUserByUserName(userName));
        return userService.login(userName, password, model);
    }

    @RequestMapping("/home")
    public String displayHome(@RequestParam String userName, Model model) {
        storyService.printStory(model);
        model.addAttribute("comments", commentService.getCommentRepository().findByOrderByCreationDateAsc());
        model.addAttribute("users", userService.getUserRepository().findAll());

        User user = userService.getUserRepository().findUserByUserName(userName);

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("loggedinuser", user);

        if (user.isAdmin()) {
            return "index_admin";
        }

        return "index_user";
    }
    @RequestMapping("/setadmin")
    public String setAdmin(@RequestParam() String userName, Model model,@RequestParam() Optional<Long> userId){
        User user  = userService.getUserRepository().findUserByUserName(userName);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", user.getUserName());

        if(userId.isPresent()){
            User userToSet = userService.getUserRepository().findById(userId);
            userToSet.setAdmin(true);
            userService.getUserRepository().save(userToSet);
        }
        model.addAttribute("users", userService.getUserRepository().findAll());

        return "admin_setadmin";
    }
}