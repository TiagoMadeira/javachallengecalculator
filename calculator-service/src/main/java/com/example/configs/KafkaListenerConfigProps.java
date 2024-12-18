package com.example.configs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "project.kafka")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaListenerConfigProps {
    private String topic;
}
