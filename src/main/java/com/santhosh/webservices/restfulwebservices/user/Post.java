package com.santhosh.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = "user")
public class Post {

    @Id
    @GeneratedValue
    private int id;
    private String description;;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post() {
    }

    public Post(int id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
