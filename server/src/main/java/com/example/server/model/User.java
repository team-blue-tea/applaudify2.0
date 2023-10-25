package com.example.server.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private UUID id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_avatar")
    private String avatarUrl;

    @Column(name = "user_bio")
    private String bio;

    @Column(name = "user_skills")
    private String skills;

    @Column(name = "user_experience")
    private String experience;

    public User() {}

    public User(UUID id, String email, String name, String avatarUrl, String bio, String skills, String experience) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
        this.skills = skills;
        this.experience = experience;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
