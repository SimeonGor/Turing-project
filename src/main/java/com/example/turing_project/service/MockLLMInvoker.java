package com.example.turing_project.service;

public class MockLLMInvoker implements LLMInvoker{
    @Override
    public void init() {

    }

    @Override
    public String invoke(String request) {
        return "This was supposed to be the answer to the question \"%s\"".formatted(request);
    }
}
