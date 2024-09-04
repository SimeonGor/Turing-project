package com.example.turing_project.llama3.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageResponse {
    @Data
    public static class Message {
        private String role;
        private String content;
    }

    private Message message;
    private Date created_at;


    public String getContent() {
        return String.join(" ", message.getContent());
    }
}
