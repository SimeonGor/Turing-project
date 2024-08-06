package com.example.turing_project.dto;

import com.example.turing_project.entity.Dialog;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DialogDto {
    private Long id;
    private String title;

    public static DialogDto of(Dialog dialog) {
        return DialogDto.builder()
                .id(dialog.getId())
                .title(dialog.getTitle())
                .build();
    }
}
