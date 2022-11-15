package com.example.project.webblog.Controllers;

import com.example.project.webblog.Entities.Story;
import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Services.StoryService;
import com.example.project.webblog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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

    @Autowired
    public UserController(UserService userService, StoryService storyService) {
        this.userService = userService;
        this.storyService = storyService;
    }

    @RequestMapping("/")
    public String displayMain(Model model) {
        storyService.printStory(model);
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String displayRegistration(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String userName, @RequestParam(required = false) String password, Model model) {
        return userService.registration(firstName, lastName, userName, password, model);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(required = false) String userName, @RequestParam(required = false) String password, Model model) {
        storyService.printStory(model);
        return userService.login(userName, password,model);
    }

    @RequestMapping("/admin")
    public String displayAdmin(@RequestParam(required = false) String userName, @RequestParam(required = false) String title, @RequestParam(required = false) String content, Model model) {
        storyService.printStory(model);

        User user = userService.getUserRepository().findUserByUserName(userName);

        Story story = new Story();

        story.setTitle(title);
        story.setContent(content);
        story.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        story.setUser(user);

        user.getStories().add(story);

        storyService.getStoryRepository().save(story);
        userService.getUserRepository().save(user);

        return "index_admin";
    }

    @RequestMapping("/admin/addstory")
    public String displayAddStory(@RequestParam(required = false) String userName, Model model) {
        storyService.printStory(model);
//        User user = userService.getUserRepository().findUserByUserName(userName);
//
//        model.addAttribute("userName", user.getUserName());

        return "admin_addstory";
    }

//    @RequestMapping(value = "/test01", method = RequestMethod.GET)
//    public String testOne(Model model) {
//
//        model.addAttribute("name", "Wewanttopassthis");
//
//        return "test01";
//    }
//
//    @RequestMapping(value = "/test02", method = RequestMethod.GET)
//    @ModelAttribute(value = "name")
//    public String testTwo(@RequestParam String userName, Model model) {
//        model.addAttribute("userName", userName);
//
//        return "test02";
//    }
}