package com.example.configs;

import com.example.CalculatorMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;

@Configuration
public class KafkaListenerConfig {

    @Bean
    public KafkaListenerErrorHandler calculatorMessageErrorHandler() {
        return (m, e) -> {
            CalculatorMessage request = (CalculatorMessage) m.getPayload();
            request.setResult(e.getCause().getMessage());
            return request;
        };
    }

}
