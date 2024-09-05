package com.example.turing_project.giga_chat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.giga-chat")
@Data
public class GigaChatProperties {
    private boolean enable;
    private String clientId;
    private String scope;
    private String clientSecret;
    private String url;

    private Long contextLimits;
}
