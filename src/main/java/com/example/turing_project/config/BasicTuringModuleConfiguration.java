package com.example.turing_project.config;

import com.example.turing_project.service.BasicTuringService;
import com.example.turing_project.service.TuringService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "turing", name = "enable-double-arch", havingValue = "false")
@Configuration
@EnableConfigurationProperties(TuringModuleProperties.class)
public class BasicTuringModuleConfiguration {
    @Bean
    public TuringService basicTuringService() {
        return new BasicTuringService();
    }
}
