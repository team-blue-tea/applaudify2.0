package com.example.server.model;

import jakarta.persistence.*;


import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "applaud")
public class Applaud {
    @Id
    @Column(name = "applaud_id")
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @Column(name = "applaud_comment")
    private String comment;

    private Date createdAt;

    public Applaud() {
    }

    public Applaud(UUID id, Member sender, Member receiver, String comment, Date createdAt) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public Member getSender() {
        return sender;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }

    public Member getReceiver() {
        return receiver;
    }

    public void setReceiver(Member receiver) {
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