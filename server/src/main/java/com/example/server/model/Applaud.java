package com.example.server.model;

import com.example.server.model.User;
import jakarta.persistence.*;


import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "applaud")
public class Applaud {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Column(name = "applaud_comment")
    private String comment;

    private Date createdAt;

    public Applaud() {
    }

    public Applaud(UUID id, User sender, User receiver, String comment, Date createdAt) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}