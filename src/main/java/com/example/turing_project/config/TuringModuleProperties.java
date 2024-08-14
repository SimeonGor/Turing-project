package com.example.turing_project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "turing")
@Data
public class TuringModuleProperties {
    private boolean enableDoubleArch = false;
}
