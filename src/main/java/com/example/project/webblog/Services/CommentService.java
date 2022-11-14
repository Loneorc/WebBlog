package com.example.project.webblog.Services;

import com.example.project.webblog.Repositories.CommentRepository;
import com.example.project.webblog.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    CommentRepository getCommentRepository();
}
