package com.example.controllers;

import com.example.CalculatorMessage;
import com.example.handlers.RequestReplyServiceHandler;
import com.example.services.CalculatorRequestReplyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

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
            @RequestParam("x") BigDecimal x,
            @RequestParam("y") BigDecimal y,
            @RequestParam(name = "precision" ,required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorMessage calculatorMessage = new CalculatorMessage(x,y,"sum",precision);

        return requestReplyServiceHandler.handleRequest(calculatorMessage);
    }

    @GetMapping("subtraction")
    public ResponseEntity<Object> subtraction(
            @RequestParam("x") BigDecimal x,
            @RequestParam("y") BigDecimal y,
            @RequestParam(name = "precision" ,required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorMessage calculatorMessage = new CalculatorMessage(x,y,"subtraction", precision);

        return requestReplyServiceHandler.handleRequest(calculatorMessage);
    }

    @GetMapping("multiplication")
    public ResponseEntity<Object> multiplication(
            @RequestParam("x") BigDecimal x,
            @RequestParam("y") BigDecimal y,
            @RequestParam(name = "precision" ,required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorMessage calculatorMessage = new CalculatorMessage(x,y,"multiplication",precision);

        return requestReplyServiceHandler.handleRequest(calculatorMessage);
    }

    @GetMapping("division")
    public ResponseEntity<Object> division(
            @RequestParam("x") BigDecimal x,
            @RequestParam("y") BigDecimal y,
            @RequestParam(name = "precision", required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorMessage calculatorMessage = new CalculatorMessage(x,y,"division",precision);

        return requestReplyServiceHandler.handleRequest(calculatorMessage);
    }

}
