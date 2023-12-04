package com.example.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private UUID id;
    private String name;
    private String jobTitle;
    private String company;
    private String avatarUrl;
}
