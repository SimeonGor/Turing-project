package com.example.turing_project.dto;

import com.example.turing_project.entity.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
@Schema(description = "request to turing")
public class QuestionDto {
    @Size(max=1000)
    private String text;

    public Question toQuestion() {
        Question question = new Question();
        question.setText(this.getText());
        return question;
    }
}
