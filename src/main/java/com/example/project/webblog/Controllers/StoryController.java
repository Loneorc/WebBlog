package com.example.project.webblog.Controllers;

import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Services.CommentService;
import com.example.project.webblog.Services.StoryService;
import com.example.project.webblog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Transactional
public class StoryController {

    private final StoryService storyService;
    private UserService userService;
    private CommentService commentService;

    @Autowired
    public StoryController(StoryService storyService, UserService userService, CommentService commentService) {
        this.storyService = storyService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @RequestMapping("/admin")
    public String displayAdmin(@RequestParam(required = false) String userName, @RequestParam(required = false) String title, @RequestParam(required = false) String content,@RequestParam() Optional<Long> storyId, Model model) {
        if (storyId.isPresent()) {
            storyService.editStory(userName, title, content, userService.getUserRepository().findUserByUserName(userName), userService.getUserRepository(),storyId, model);
        }else {
            storyService.addStory(userName, title, content, userService.getUserRepository().findUserByUserName(userName), userService.getUserRepository(), model);
        }

        model.addAttribute("comments", commentService.getCommentRepository().findByOrderByCreationDateAsc());
        model.addAttribute("comments", commentService.getCommentRepository().findByOrderByCreationDateAsc());
        model.addAttribute("users", userService.getUserRepository().findAll());
        model.addAttribute("firstName", userService.getUserRepository().findUserByUserName(userName).getFirstName());
        model.addAttribute("userName", userService.getUserRepository().findUserByUserName(userName).getUserName());

        return "index_admin";
    }

    @RequestMapping("/admin/addstory")
    public String displayAddStory(@RequestParam() String userName, Model model) {

        storyService.displayAdminForm(userName, model, userService.getUserRepository().findUserByUserName(userName), commentService, userService);

        return "admin_addstory";
    }

    @RequestMapping("/deletestory")
    public String deleteStory (@RequestParam() String userName, @RequestParam()long storyId, Model model) {
        commentService.getCommentRepository().deleteAllByStoryId(storyId);
        storyService.getStoryRepository().deleteById(storyId);

        storyService.printStory(model);
        User user = userService.getUserRepository().findUserByUserName(userName);

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", user.getUserName());

        model.addAttribute("comments", commentService.getCommentRepository().findByOrderByCreationDateAsc());
        model.addAttribute("users", userService.getUserRepository().findAll());
        return "index_admin" ;
    }

    @RequestMapping("/editstory")
    public String displayEditStory(@RequestParam() String userName, @RequestParam() long storyId, Model model) {
        model.addAttribute("s", storyService.getStoryRepository().findStoryById(storyId));
        storyService.displayAdminForm(userName, model, userService.getUserRepository().findUserByUserName(userName), commentService, userService);

        return "admin_addstory";
    }

}
