package com.example.server.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;


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

    private boolean isRead;

    private boolean isPublished;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    public Applaud() {
    }

    public Applaud(UUID id, Member sender, Member receiver, String comment, boolean isRead, boolean isPublished, Date createdAt) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.comment = comment;
        this.isRead = isRead;
        this.isPublished = isPublished;
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

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}