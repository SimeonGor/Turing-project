package com.example.turing_project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "response form Turing")
public class AnswerDto {
    private String text;
    private String document;
}
