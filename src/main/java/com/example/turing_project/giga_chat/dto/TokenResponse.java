package com.example.turing_project.giga_chat.dto;

import lombok.Data;

@Data
public class TokenResponse {
    private String access_token;
    private Long expires_at;
}
