package com.example.controllers;

import com.example.RestApplication;
import com.example.domain.CalculatorRequest;
import com.example.handlers.RequestReplyServiceHandler;
import com.example.services.CalculatorRequestReplyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/")
public class CalculatorController {

    private final RequestReplyServiceHandler requestReplyServiceHandler;

    public CalculatorController(CalculatorRequestReplyService calculatorRequestService, RequestReplyServiceHandler requestReplyServiceHandler){
        this.requestReplyServiceHandler = requestReplyServiceHandler;
    }

    @GetMapping("sum")
    public ResponseEntity<Object> sum(
            @RequestParam() BigDecimal x,
            @RequestParam BigDecimal y,
            @RequestParam(required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"sum",precision);

        return requestReplyServiceHandler.handleRequest(calculatorRequest);
    }

    @GetMapping("subtraction")
    public ResponseEntity<Object> subtraction(
            @RequestParam BigDecimal x,
            @RequestParam BigDecimal y,
            @RequestParam(required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"subtraction", precision);

        return requestReplyServiceHandler.handleRequest(calculatorRequest);
    }

    @GetMapping("multiplication")
    public ResponseEntity<Object> multiplication(
            @RequestParam BigDecimal x,
            @RequestParam BigDecimal y,
            @RequestParam(required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"multiplication",precision);

        return requestReplyServiceHandler.handleRequest(calculatorRequest);
    }

    @GetMapping("division")
    public ResponseEntity<Object> division(
            @RequestParam BigDecimal x,
            @RequestParam BigDecimal y,
            @RequestParam(required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"division",precision);

        return requestReplyServiceHandler.handleRequest(calculatorRequest);
    }

}
