package com.example.listener;

import com.example.domain.CalculatorRequest;
import com.example.exceptions.OperationDoesNotExistException;
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
    public CalculatorRequest listens (CalculatorRequest request) throws OperationDoesNotExistException {

        float x = request.getX();
        float y = request.getY();
        String operation = request.getOperation();
        request.setResult(String.valueOf(resolveOperation(operation,x, y)));

        return request ;
        }

    private float resolveOperation(String operation, float x, float y) throws OperationDoesNotExistException {

        return switch (operation) {
            case "sum" -> calculatorService.sum(x, y);
            case "subtraction" -> calculatorService.subtraction(x, y);
            case "multiplication" -> calculatorService.multiplication(x, y);
            case "division" -> calculatorService.division(x, y);
            default -> throw new OperationDoesNotExistException(operation);
        };
    }



    }


