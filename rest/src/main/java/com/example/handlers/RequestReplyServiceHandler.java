package com.example.handlers;

import com.example.domain.CalculatorRequest;
import com.example.services.CalculatorRequestReplyService;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class RequestReplyServiceHandler {
    private final CalculatorRequestReplyService calculatorRequestService;

    public RequestReplyServiceHandler(CalculatorRequestReplyService calculatorRequestService) {
        this.calculatorRequestService = calculatorRequestService;
    }
    public  ResponseEntity<Object> handleRequest(CalculatorRequest request) {
        try {
            CalculatorRequest result = calculatorRequestService.calculatorRequestReply(request);
            return getSuccessfulEntity(result.getResult());

        } catch (ExecutionException e) {
            return getExecutionFailEntity(e.getCause().getMessage());
        } catch (InterruptedException e) {
            return getInterruptedExceptionEntity();
        }

    }

    private ResponseEntity<Object> getSuccessfulEntity(String result) {
        return ResponseEntity.ok().header("Request.id", MDC.get("Request.id")).body(Map.of(
                "result", result));
    }
    private ResponseEntity<Object> getExecutionFailEntity(String cause) {
        return ResponseEntity.badRequest().header("Request.id", MDC.get("Request.id")).body(Map.of(
                "BadRequest", cause));
    }
    private ResponseEntity<Object> getInterruptedExceptionEntity() {
        return ResponseEntity.internalServerError().header("Request.id", MDC.get("Request.id")).body(Map.of(
                "Error", "Something went wrong"));
    }

}
