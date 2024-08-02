package com.example.turing_project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "instance of message")
public class MessageDto {
    private Long id;
    private LocalDateTime created;
    private QuestionDto question;
    private AnswerDto answer;
}
