package com.example.turing_project.config;


import com.example.turing_project.service.DocumentService;
import com.example.turing_project.service.DoubleArchTuringService;
import com.example.turing_project.service.TuringService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "app.turing", name = "enable-double-arch", havingValue = "true")
@Configuration
@EnableConfigurationProperties(TuringModuleProperties.class)
public class DoubleArchTuringModuleConfiguration {
    @Bean
    public TuringService doubleArchTuringService(DocumentService documentService) {
        return new DoubleArchTuringService(documentService);
    }
}
