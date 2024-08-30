package com.example.turing_project.llama3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.llama")
@Data
public class LlamaProperties {
    private String url;
}
