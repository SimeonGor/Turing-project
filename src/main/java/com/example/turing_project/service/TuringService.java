package com.example.turing_project.service;

import com.example.turing_project.dto.AnswerDto;
import com.example.turing_project.dto.HistoryContext;

public interface TuringService {
    Long getHistoryContextLimits();
    AnswerDto handle(String request, HistoryContext historyContext);
}
