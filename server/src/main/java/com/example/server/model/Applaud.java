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

    private String comment;

    private Date createdAt;
}