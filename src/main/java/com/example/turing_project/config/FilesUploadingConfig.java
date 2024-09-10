package com.example.turing_project.config;

import com.example.turing_project.service.StorageService;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.unit.DataSize;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties(StorageProperties.class)
public class FilesUploadingConfig {
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("12MB"));
        factory.setMaxRequestSize(DataSize.parse("12MB"));
        return factory.createMultipartConfig();
    }
}
