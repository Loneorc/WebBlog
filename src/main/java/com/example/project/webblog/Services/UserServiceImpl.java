package com.example.project.webblog.Services;

import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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

    @Override
    public String login(String userName, String password, Model model) {

        if (userName == null || password == null) {
            model.addAttribute("isNull", true);
            model.addAttribute("result", "Please fill up username & password.");
            return "index";
        }
        if (userName.isEmpty() || password.isEmpty()) {
            model.addAttribute("isEmpty", true);
            model.addAttribute("result", "Please fill up username & password.");
            return "index";
        }
        if (!userRepository.existsUserByUserNameAndPassword(userName, password)) {
            model.addAttribute("isNotExists", true);
            model.addAttribute("result", "Username or password is incorrect.");
            return "index";
        }

        User user = userRepository.findUserByUserName(userName);

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("loggedinuser", userRepository.findUserByUserName(userName));

        if (user.isAdmin()) {
            return "index_admin";
        }

        return "index_user";
    }

    @Override
    public void printUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
    }

    @Override
    public void setAdmin(String userName, Model model, Optional<Long> userId) {
        User user  = userRepository.findUserByUserName(userName);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", user.getUserName());

        if(userId.isPresent()){
            User userToSet = userRepository.findById(userId);
            userToSet.setAdmin(true);
            userRepository.save(userToSet);
        }

        printUsers(model);
    }

    @Override
    public boolean loggedInUserIsAdmin(String userName, Model model) {
        User user = userRepository.findUserByUserName(userName);

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("loggedinuser", user);

        if (user.isAdmin()) {
            return true;
        }

        return false;
    }
}





