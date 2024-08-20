package com.example.turing_project.config;

import com.example.turing_project.giga_chat.config.GigaChatProperties;
import com.example.turing_project.service.BasicTuringService;
import com.example.turing_project.giga_chat.service.GigaChatInvoker;
import com.example.turing_project.service.LLMInvoker;
import com.example.turing_project.service.TuringService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "app.turing", name = "enable-double-arch", havingValue = "false")
@Configuration
@EnableConfigurationProperties(GigaChatProperties.class)
public class BasicTuringModuleConfiguration {
    @Bean
    public TuringService basicTuringService(LLMInvoker gigaChat) {
        return new BasicTuringService(gigaChat);
    }

    @Bean
    public LLMInvoker gigaChat(GigaChatProperties properties) {
        return new GigaChatInvoker(properties);
    }
}
