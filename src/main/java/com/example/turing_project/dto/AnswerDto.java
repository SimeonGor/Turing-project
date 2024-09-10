package com.example.turing_project.dto;

import com.example.turing_project.entity.Answer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "response form Turing")
public class AnswerDto {
    private String text;
    private String document;

    public Answer toAnswer() {
        Answer answer = new Answer();
        answer.setType("text");
        answer.setDocument(this.getDocument());
        answer.setText(this.getText());
        return answer;
    }
}
