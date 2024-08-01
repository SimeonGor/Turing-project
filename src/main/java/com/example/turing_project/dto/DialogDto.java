package com.example.turing_project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DialogDto {
    private Long id;
    private String title;
}
