package com.example.project.webblog.Repositories;

import com.example.project.webblog.Entities.Story;
import com.example.project.webblog.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface StoryRepository extends CrudRepository<Story, Long> {
}
