package com.example.project.webblog.Services;

import com.example.project.webblog.Repositories.StoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface StoryService {
    StoryRepository getStoryRepository();

    String printStory(Model model);
}
