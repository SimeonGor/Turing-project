package com.example.turing_project.giga_chat.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class MessageResponse {
    @Data
    public static class Choice {
        @Data
        public static class Message {
            private String role;
            private String content;
        }

        private Message message;
        private Integer index;
        private String finish_reason;
    }

    private List<Choice> choices;
    private Date created;


    public String getContent() {
        return String.join(" ", choices.stream().map(choice -> choice.getMessage().getContent()).toList());
    }
}
