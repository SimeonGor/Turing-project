package com.example.turing_project.config;

import com.example.turing_project.service.BasicTuringService;
import com.example.turing_project.service.LLMInvoker;
import com.example.turing_project.service.TuringService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "app.turing", name = "enable-double-arch", havingValue = "false")
@Configuration
public class BasicTuringModuleConfiguration {
    @Bean
    public TuringService basicTuringService(LLMInvoker llmInvoker) {
        llmInvoker.init();
        return new BasicTuringService(llmInvoker);
    }
}
