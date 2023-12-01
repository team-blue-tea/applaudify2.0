package com.example.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "applaud")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}