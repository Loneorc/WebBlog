package com.example.project.webblog.Repositories;

import com.example.project.webblog.Entities.Story;
import com.example.project.webblog.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StoryRepository extends CrudRepository<Story, Long> {

    Story findStoryById(long id);

    Story findStoryById(Optional<Long> id);
    List<Story> findByOrderByCreationDateDesc();
}
