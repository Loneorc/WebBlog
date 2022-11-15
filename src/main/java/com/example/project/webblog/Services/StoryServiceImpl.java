package com.example.project.webblog.Services;

import com.example.project.webblog.Entities.Story;
import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Repositories.StoryRepository;
import com.example.project.webblog.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class StoryServiceImpl implements StoryService{

    private StoryRepository storyRepository;

    @Autowired
    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public StoryRepository getStoryRepository() {
        return storyRepository;
    }

    @Override
    public String printStory(Model model) {
        model.addAttribute("story", storyRepository.findAll());
        return "index";
    }

    @Override
    public void addStory(String userName, String title, String content, User user, UserRepository userRepository, Model model) {
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", userName);

        Story story = new Story();
        story.setTitle(title);
        story.setContent(content);
        story.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        story.setUser(user);

        user.getStories().add(story);

        storyRepository.save(story);
        userRepository.save(user);

        printStory(model);
    }

    @Override
    public void displayAdminForm(String userName, Model model, User user) {
        printStory(model);

        //User user = userService.getUserRepository().findUserByUserName(userName);

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", user.getUserName());
    }


}
