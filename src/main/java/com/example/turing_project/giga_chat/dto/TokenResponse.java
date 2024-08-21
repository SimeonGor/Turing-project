package com.example.turing_project.giga_chat.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TokenResponse {
    private String access_token;
    private LocalDateTime expired_at;
}
