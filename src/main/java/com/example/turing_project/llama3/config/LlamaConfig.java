package com.example.turing_project.llama3.config;

import com.example.turing_project.llama3.service.LlamaInvoker;
import com.example.turing_project.service.LLMInvoker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "app.llama", name = "enable", havingValue = "true")
@Configuration
@EnableConfigurationProperties(LlamaProperties.class)
public class LlamaConfig {
    @Bean
    public LLMInvoker llamaChat(LlamaProperties properties) {
        return new LlamaInvoker(properties);
    }
}
