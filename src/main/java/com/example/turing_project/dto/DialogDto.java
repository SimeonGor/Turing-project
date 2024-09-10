package com.example.turing_project.dto;

import com.example.turing_project.entity.Dialog;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DialogDto {
    private Long id;
    private String title;
    private LocalDateTime created;
    private LocalDateTime modified;

    public static DialogDto of(Dialog dialog) {
        return DialogDto.builder()
                .id(dialog.getId())
                .title(dialog.getTitle())
                .created(dialog.getCreated())
                .modified(dialog.getModified())
                .build();
    }
}
