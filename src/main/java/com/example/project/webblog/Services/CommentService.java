package com.example.project.webblog.Services;

import com.example.project.webblog.Repositories.CommentRepository;
import com.example.project.webblog.Repositories.StoryRepository;
import com.example.project.webblog.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface CommentService {

    CommentRepository getCommentRepository();

    void addComment(String userName, String commentContent, Model model, long storyId,
                    UserRepository userRepository, StoryRepository storyRepository);

    void deleteComment(String userName, Model model, long commentId, UserRepository userRepository);

    void printComments(Model model);
}
