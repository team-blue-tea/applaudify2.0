package com.example.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
}
