package com.example.project.webblog.Services;

import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Repositories.StoryRepository;
import com.example.project.webblog.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public interface StoryService {
    StoryRepository getStoryRepository();

    void printStory(Model model);

    void addStory(String userName, String title, String content, User user, UserRepository userRepository, Model model);
    void displayAdminForm(String userName, Model model, User user, CommentService commentService, UserService userService);

    void editStory(String userName, String title, String content, User user, UserRepository userRepository, Optional<Long> storyId, Model model);
}
