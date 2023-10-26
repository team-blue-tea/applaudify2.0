package com.example.server.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private UUID id;

    private String email;

    private String name;

    private String jobTitle;

    private String company;

    private String avatarUrl;

    @Column(length = 1000)
    private String bio;

    @Column(length = 1000)
    private String skills;

    @Column(length = 1000)
    private String experience;

    public Member() {}

    public Member(UUID id, String email, String name, String jobTitle, String company, String avatarUrl, String bio, String skills, String experience) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.jobTitle = jobTitle;
        this.company = company;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
