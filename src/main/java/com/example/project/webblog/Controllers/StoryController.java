package com.example.project.webblog.Controllers;

import com.example.project.webblog.Services.StoryService;
import com.example.project.webblog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoryController {

    private final StoryService storyService;
    private UserService userService;

    @Autowired
    public StoryController(StoryService storyService, UserService userService) {
        this.storyService = storyService;
        this.userService = userService;
    }

    @RequestMapping("/admin")
    public String displayAdmin(@RequestParam(required = false) String userName, @RequestParam(required = false) String title, @RequestParam(required = false) String content, Model model) {
        storyService.addStory(userName, title, content, userService.getUserRepository().findUserByUserName(userName), userService.getUserRepository(), model);

        return "index_admin";
    }

    @RequestMapping("/admin/addstory")
    public String displayAddStory(@RequestParam() String userName, Model model) {
        storyService.displayAdminForm(userName, model, userService.getUserRepository().findUserByUserName(userName));

        return "admin_addstory";
    }
}
