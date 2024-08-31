package com.example.turing_project.service;

public class MockLLMInvoker implements LLMInvoker{
    @Override
    public void init() {

    }

    @Override
    public String invoke(String request) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "This was supposed to be the answer to the question \"%s\"".formatted(request);
    }
}
