package com.example.project.webblog.Services;

import com.example.project.webblog.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface UserService {
    UserRepository getUserRepository();

    String registration(String firstName, String lastName, String userName, String password, Model model);
    String login(String userName, String password, Model model);

}


