package com.example.turing_project.service;

import com.example.turing_project.dto.HistoryContext;

public class MockLLMInvoker implements LLMInvoker{
    @Override
    public void init() {

    }

    @Override
    public Long getHistoryContextLimits() {
        return 0L;
    }

    @Override
    public String invoke(String request, HistoryContext historyContext) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "This was supposed to be the answer to the question \"%s\"".formatted(request);
    }
}
