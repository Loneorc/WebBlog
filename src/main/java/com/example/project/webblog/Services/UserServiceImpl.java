package com.example.project.webblog.Services;

import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public String registration(String firstName, String lastName, String userName, String password, Model model) {

        if (firstName == null || lastName == null || userName == null || password == null) {
            return "registration";
        }

        if (userRepository.existsUserByUserName(userName)) {
            model.addAttribute("isAlreadyExists", true);

            return "registration";
        }

        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            model.addAttribute("isAnyOfThemEmpty", true);

            return "registration";
        }

        User user = new User(firstName, lastName, userName, password);
        userRepository.save(user);

        model.addAttribute("isSuccessful", true);

        return "registration";
    }
}
