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
    public String displayRegistration(@RequestParam(required = false, defaultValue = "") String firstName, @RequestParam(required = false, defaultValue = "") String lastName, @RequestParam(required = false, defaultValue = "") String userName, @RequestParam(required = false, defaultValue = "") String password, Model model) {
        return userService.registration(firstName, lastName, userName, password, model);
    }
}
