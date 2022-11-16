package com.example.project.webblog.Services;

import com.example.project.webblog.Entities.Comment;
import com.example.project.webblog.Entities.Story;
import com.example.project.webblog.Entities.User;
import com.example.project.webblog.Repositories.CommentRepository;
import com.example.project.webblog.Repositories.StoryRepository;
import com.example.project.webblog.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentRepository getCommentRepository() {
        return commentRepository;
    }

    @Override
    public void addComment(String userName, String commentContent, Model model, long storyId,
                             UserRepository userRepository, StoryRepository storyRepository) {


        User user = userRepository.findUserByUserName(userName);
        Story story = storyRepository.findStoryById(storyId);

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", userName);

        Comment comment = new Comment();
        comment.setContent(commentContent);
        comment.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        comment.setUser(user);

        comment.setStory(story);

        user.getComments().add(comment);
        story.getComments().add(comment);


        commentRepository.save(comment);
        storyRepository.save(story);
        userRepository.save(user);
    }

    @Override
    public void deleteComment(String userName, Model model, long commentId, UserRepository userRepository) {

        User user = userRepository.findUserByUserName(userName);

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("userName", userName);

        commentRepository.deleteById(commentId);
    }

    @Override
    public void printComments(Model model) {
        model.addAttribute("comments", commentRepository.findByOrderByCreationDateAsc());
    }


}
