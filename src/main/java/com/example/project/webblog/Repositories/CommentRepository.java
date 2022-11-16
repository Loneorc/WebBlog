package com.example.project.webblog.Repositories;

import com.example.project.webblog.Entities.Comment;
import com.example.project.webblog.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByOrderByCreationDateAsc();

    void deleteAllByStoryId(long storyId);
}
