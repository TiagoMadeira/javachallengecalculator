package com.example.listener;

import com.example.CalculatorMessage;
import com.example.exceptions.OperationDoesNotExistException;
import com.example.service.impl.CalculatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@Component
public class CalculatorRequestListener {
    private static Logger logger = LogManager.getLogger(CalculatorRequestListener.class);
    private final CalculatorService calculatorService;


    public CalculatorRequestListener(CalculatorService calculatorService) {

        this.calculatorService = calculatorService;
    }

    @KafkaListener(id = "calculator", topics = "${project.kafka.topic}")
    @SendTo
    public CalculatorMessage listens(CalculatorMessage request, @Headers Map<String, byte[]> headers) throws OperationDoesNotExistException, ArithmeticException {

        String id = new String(headers.get("Request.id"), StandardCharsets.UTF_8);
        //Log Request
        logRequest(request, id);

        BigDecimal x = request.getX();
        BigDecimal y = request.getY();
        String operation = request.getOperation();
        int precision = request.getPrecision();

        request.setResult(String.valueOf(resolveOperation(operation, x, y, precision)));

        //Log reply
        logReply(request);
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

    private void logRequest(CalculatorMessage request, String id) {
        MDC.put("Request.id", id);
        MDC.put("Kafka.Request.Operation", request.getOperation());
        MDC.put("Kafka.Request.xOperand", request.getX().toString());
        MDC.put("Kafka.Request.yOperand", request.getY().toString());
        MDC.put("Kafka.Request.Precision", String.valueOf(request.getPrecision()));
        logger.info("[calculator-service][Kafka][Input] Kafka request receive!");
    }

    private void logReply(CalculatorMessage request) {
        MDC.put("Kafka.reply.result", request.getOperation());
        logger.info("[calculator-service][Kafka][Output] Kafka reply sent!");
    }
}


