package com.example.listener;

import com.example.domain.CalculatorRequest;
import com.example.service.impl.CalculatorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class CalculatorRequestListener {

    private final CalculatorService calculatorService;

    public CalculatorRequestListener(CalculatorService calculatorService){

        this.calculatorService = calculatorService;
    }

    @KafkaListener(id = "calculator", topics = "calculator.requests")
    @SendTo
    public String listens (CalculatorRequest request){

        return "Nice";
    }
}
