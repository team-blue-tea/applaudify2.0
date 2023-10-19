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

    @Column(name = "user_name")
    private String name;
    
    public User() {}
    
    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
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
}
