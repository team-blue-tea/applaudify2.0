package com.example.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplaudDTO {
    private UUID id;
    private MemberDTO sender;
    private MemberDTO receiver;
    private String comment;
    private boolean isRead;
    private boolean isPublished;
    private Date createdAt;
}
