package com.example.listener;

import com.example.domain.CalculatorRequest;
import com.example.exceptions.OperationDoesNotExistException;
import com.example.service.impl.CalculatorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class CalculatorRequestListener {

    private final CalculatorService calculatorService;

    public CalculatorRequestListener(CalculatorService calculatorService){

        this.calculatorService = calculatorService;
    }

    @KafkaListener(id = "calculator", topics = "calculator.requests")
    @SendTo
    public CalculatorRequest listens (CalculatorRequest request) throws OperationDoesNotExistException {
        BigDecimal x = request.getX();
        BigDecimal y = request.getY();
        String operation = request.getOperation();
        int precision = request.getPrecision();

        request.setResult(String.valueOf(resolveOperation(operation,x, y, precision)));
        return request ;
        }

    private BigDecimal resolveOperation(String operation, BigDecimal x, BigDecimal y, int precision) throws OperationDoesNotExistException {

        return switch (operation) {
            case "sum" -> calculatorService.sum(x, y, precision);
            case "subtraction" -> calculatorService.subtraction(x, y, precision);
            case "multiplication" -> calculatorService.multiplication(x, y, precision);
            case "division" -> calculatorService.division(x, y, precision);
            default -> throw new OperationDoesNotExistException(operation);
        };
    }



    }


