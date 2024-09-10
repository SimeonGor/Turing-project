package com.example.turing_project.giga_chat.config;

import com.example.turing_project.giga_chat.service.GigaChatInvoker;
import com.example.turing_project.service.LLMInvoker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "app.giga-chat", name = "enable", havingValue = "true")
@Configuration
@EnableConfigurationProperties(GigaChatProperties.class)
public class GigaChatConfiguration {
    @Bean
    public LLMInvoker gigaChat(GigaChatProperties properties) {
        return new GigaChatInvoker(properties);
    }
}
