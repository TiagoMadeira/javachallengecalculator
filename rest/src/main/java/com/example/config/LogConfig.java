package com.example.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig {

    public LogConfig() {
    }

    @Bean
    public Logger loggernow() {
        return LogManager.getLogger("lognow");
    }

    @Bean
    public Logger rootLogger() {
        return LogManager.getRootLogger();
    }
}
