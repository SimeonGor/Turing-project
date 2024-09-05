package com.example.turing_project.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HistoryContext {
    List<MessageDto> messages;
}
