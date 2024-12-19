package com.example.listener;

import com.example.CalculatorMessage;
import com.example.exceptions.OperationDoesNotExistException;
import com.example.service.impl.CalculatorService;
import com.example.utils.logUtils.LogHelper;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@Component
public class CalculatorRequestListener {

    private static final LogHelper loggerHelper = new LogHelper();
    private final CalculatorService calculatorService;

    public CalculatorRequestListener(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @KafkaListener(id = "calculator", topics = "${project.kafka.topic}",errorHandler = "calculatorMessageErrorHandler")
    @SendTo
    public CalculatorMessage listens(CalculatorMessage request, @Headers Map<String, byte[]> headers) throws OperationDoesNotExistException, ArithmeticException {

            //Propagate Request id
            String id = new String(headers.get("Request.id"), StandardCharsets.UTF_8);
            MDC.put("Request.id", id);
            //Log Request
            loggerHelper.loadAndLogCalculatorMessageToMDC(request,
                    "[calculator-service][Kafka][Input] Kafka request receive!");

            BigDecimal x = request.getX();
            BigDecimal y = request.getY();
            String operation = request.getOperation();
            int precision = request.getPrecision();

            //Process Request
            request.setResult(String.valueOf(resolveOperation(operation, x, y, precision)));

            //Log reply
            loggerHelper.loadAndLogCalculatorMessageToMDC(request,
                    "[calculator-service][Kafka][Output] Kafka reply sent!");

            return request;
    }


    private BigDecimal resolveOperation(String operation, BigDecimal x, BigDecimal y, int precision) throws OperationDoesNotExistException, ArithmeticException {

        return switch (operation) {
            case "sum" -> calculatorService.sum(x, y, precision);
            case "subtraction" -> calculatorService.subtraction(x, y, precision);
            case "multiplication" -> calculatorService.multiplication(x, y, precision);
            case "division" -> calculatorService.division(x, y, precision);
            default -> throw new OperationDoesNotExistException(operation);
        };
    }

}


