package com.example.controllers;



import com.example.domain.CalculatorRequest;
import com.example.services.CalculatorRequestReplyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/")
public class CalculatorController {

    private final CalculatorRequestReplyService calculatorRequestService;

    public CalculatorController( CalculatorRequestReplyService calculatorRequestService){
        this.calculatorRequestService = calculatorRequestService;
    }

    @GetMapping("sum")
    public Object sum(@RequestParam Float x, @RequestParam Float y ) throws ExecutionException, InterruptedException, JsonProcessingException {

        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"sum");
        Object result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return result;
    }

    @GetMapping("subtraction")
    public Object subtraction(@RequestParam Float x, @RequestParam Float y ) throws ExecutionException, InterruptedException, JsonProcessingException {
        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"subtraction");
        Object  result = calculatorRequestService.calculatorRequestReply(calculatorRequest).value();

        return result;
    }

    @GetMapping("multiplication")
    public Object multiplication(@RequestParam Float x, @RequestParam Float y ) throws ExecutionException, InterruptedException, JsonProcessingException {
        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"multiplication");
        Object result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return result;
    }

    @GetMapping("division")
    public Object division(@RequestParam Float x, @RequestParam Float y) throws ExecutionException, InterruptedException, JsonProcessingException {
        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"division");
        Object result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return result;
    }
}
