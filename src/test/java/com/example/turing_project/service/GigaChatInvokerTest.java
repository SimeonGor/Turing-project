package com.example.turing_project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class GigaChatInvokerTest {
    @Autowired
    private ApplicationContext context;
    private LLMInvoker gigaChat;
    @BeforeEach
    void setUp() {
        gigaChat = context.getBean(LLMInvoker.class);
    }

    @Test
    void init() {
        gigaChat.init();
        assert(true);
    }

    @Test
    void shouldReturnResponse() {
        gigaChat.init();
        String string = gigaChat.invoke("Столица Франции");
        Assertions.assertNotNull(string);
    }
}