package com.motos.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String imageUrl;

    private int likes = 0;

    private int loves = 0;

    private int hahas = 0;

    private int wows = 0;

    private int sads = 0;

    private int angrys = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
    }

    public Post(String text, String imageUrl) {
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getLikes() {
        return likes;
    }

    public int getLoves() {
        return loves;
    }

    public int getHahas() {
        return hahas;
    }

    public int getWows() {
        return wows;
    }

    public int getSads() {
        return sads;
    }

    public int getAngrys() {
        return angrys;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setLoves(int loves) {
        this.loves = loves;
    }

    public void setHahas(int hahas) {
        this.hahas = hahas;
    }

    public void setWows(int wows) {
        this.wows = wows;
    }

    public void setSads(int sads) {
        this.sads = sads;
    }

    public void setAngrys(int angrys) {
        this.angrys = angrys;
    }

    public void setUser(User user) {
        this.user = user;
    }
}