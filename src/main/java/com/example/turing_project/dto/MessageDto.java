package com.example.turing_project.dto;

import com.example.turing_project.entity.Message;
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

    public static MessageDto of(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .created(message.getCreated())
                .answer(AnswerDto.builder()
                        .text(message.getAnswer().getText())
                        .document(message.getAnswer().getDocument())
                        .build()
                )
                .question(QuestionDto.builder()
                        .text(message.getQuestion().getText())
                        .build()
                )
                .build();
    }
}
