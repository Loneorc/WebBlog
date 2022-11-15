package com.example.project.webblog.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Comment {

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    Story story;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String content;
    private String creationDate;


    public Comment(String content) {
        this.content = content;
    }

    public Comment() {

    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
