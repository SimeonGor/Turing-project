package com.example.turing_project.llama3.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageRequest {
    @Data
    @Builder
    public static class Message {
        @Builder.Default
        private String role = "user";
        private String content;
    }
    @Builder.Default
    private String model = "llama3";
    private List<Message> messages;
    @Builder.Default
    private Boolean stream = false;
}
