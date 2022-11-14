package com.example.project.webblog.Services;

import com.example.project.webblog.Repositories.StoryRepository;
import org.springframework.stereotype.Service;

@Service
public interface StoryService {
    StoryRepository getStoryRepository();
}
