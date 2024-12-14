package com.example.configs;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "calculator")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculatorConfig {

    private  int defaultPrecision;

}
