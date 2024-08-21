package com.example.turing_project.service;

public interface LLMInvoker {
    void init();

    String invoke(String request);
}
