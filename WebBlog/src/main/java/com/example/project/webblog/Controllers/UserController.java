package com.example.project.webblog.Controllers;

import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String displayMain() {
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String displayRegistration(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String userName, @RequestParam(required = false) String password, Model model) {
        return userService.registration(firstName, lastName, userName, password, model);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(required = false) String userName, @RequestParam(required = false) String password, Model model) {
        if (userName == null || password == null) {
            model.addAttribute("isNull", true);
            model.addAttribute("result", "Please fill up username & password.");
            return "index";
        }
        if (!userService.getUserRepository().existsUserByUserNameAndPassword(userName, password)) {
            model.addAttribute("isExists", false);
            model.addAttribute("result", "Username or password is incorrect.");
            return "index";
        }
        return "index_user";
    }
}