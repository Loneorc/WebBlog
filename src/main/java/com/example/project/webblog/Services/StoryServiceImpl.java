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
import java.util.Optional;

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
    public void printStories(Model model) {
        model.addAttribute("story", storyRepository.findByOrderByCreationDateDesc());
    }

    @Override
    public boolean addStory(String userName, String title, String content, User user, UserRepository userRepository, Model model) {
        if (title.isEmpty() || content.isEmpty()) {
            model.addAttribute("isEmpty", true);

            return false;
        }

        if (title == null || content == null) {
            model.addAttribute("isNull", true);

            return false;
        }

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

        printStories(model);

        return true;
    }

    @Override
    public void displayAdminForm(String userName, Model model, User user) {
        printStories(model);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", userName);
    }
    @Override
    public boolean editStory(String userName, String title, String content, User user, UserRepository userRepository, Optional<Long> storyId, Model model) {
        if (title.isEmpty() || content.isEmpty()) {
            model.addAttribute("isEmpty", true);

            return false;
        }

        if (title == null || content == null) {
            model.addAttribute("isNull", true);

            return false;
        }

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", userName);

        Story story = storyRepository.findStoryById(storyId);
        story.setTitle(title);
        story.setContent(content);
        story.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        story.setUser(user);

        user.getStories().add(story);

        storyRepository.save(story);
        userRepository.save(user);

        printStories(model);

        return true;
    }

}
