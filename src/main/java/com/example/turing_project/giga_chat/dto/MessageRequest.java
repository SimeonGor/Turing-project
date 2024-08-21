package com.example.turing_project.giga_chat.dto;

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
    private String model = "GigaChat";
    private List<Message> messages;
}
