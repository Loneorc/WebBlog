package com.example.project.webblog.Services;

import com.example.project.webblog.Entities.Story;
import com.example.project.webblog.Repositories.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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


}
