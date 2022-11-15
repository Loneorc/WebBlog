package com.example.project.webblog.Controllers;

import com.example.project.webblog.Entities.Story;
import com.example.project.webblog.Repositories.StoryRepository;
import com.example.project.webblog.Services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class StoryController {

    private final StoryService storyService;
    private Model model;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @RequestMapping("/story")
    public String printAll(Model model) {


        Story story = new Story("Some Title","Lot lot lot lot lot of text", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Story story2 = new Story("java.time.LocalDateTime Class in Java","java.time.LocalDateTime class, " +
                "introduced in Java 8, represents a local date-time object without timezone information." +
                "java.time.LocalDateTime class, introduced in Java 8, represents a local date-time object without timezone information." +
                "java.time.LocalDateTime class, introduced in Java 8, represents a local date-time object without timezone information." +
                "java.time.LocalDateTime class, introduced in Java 8, represents a local date-time object without timezone information." +
                "java.time.LocalDateTime class, introduced in Java 8, represents a local date-time object without timezone information." +
                "java.time.LocalDateTime class, introduced in Java 8, represents a local date-time object without timezone information."
                , LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        storyService.getStoryRepository().save(story);
        storyService.getStoryRepository().save(story2);
        model.addAttribute("story", storyService.getStoryRepository().findAll());
        return "index";
    }

}
