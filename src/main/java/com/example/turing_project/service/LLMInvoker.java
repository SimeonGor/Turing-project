package com.example.turing_project.service;

import com.example.turing_project.dto.HistoryContext;

public interface LLMInvoker {
    void init();

    Long getHistoryContextLimits();

    String invoke(String request, HistoryContext historyContext);
}
