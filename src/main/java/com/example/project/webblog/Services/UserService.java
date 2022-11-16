package com.example.project.webblog.Services;

import com.example.project.webblog.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public interface UserService {
    UserRepository getUserRepository();

    String registration(String firstName, String lastName, String userName, String password, Model model);
    String login(String userName, String password, Model model);

    void printUsers(Model model);

    void setAdmin(String userName, Model model, Optional<Long> userId);

    boolean loggedInUserIsAdmin(String userName, Model model);
}


