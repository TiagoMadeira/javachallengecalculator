package com.example.controllers;



import com.example.domain.CalculatorRequest;
import com.example.services.CalculatorRequestReplyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/")
public class CalculatorController {

    private final CalculatorRequestReplyService calculatorRequestService;

    public CalculatorController( CalculatorRequestReplyService calculatorRequestService){
        this.calculatorRequestService = calculatorRequestService;
    }

    @GetMapping("sum")
    public ResponseEntity<Object> sum(@RequestParam Float x, @RequestParam Float y ) throws ExecutionException, InterruptedException, JsonProcessingException {

        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"sum");
        CalculatorRequest result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return getResponseEntity(result.getResult());
    }

    @GetMapping("subtraction")
    public ResponseEntity<Object> subtraction(@RequestParam Float x, @RequestParam Float y ) throws ExecutionException, InterruptedException, JsonProcessingException {
        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"subtraction");
        CalculatorRequest  result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return getResponseEntity(result.getResult());
    }

    @GetMapping("multiplication")
    public ResponseEntity<Object> multiplication(@RequestParam Float x, @RequestParam Float y ) throws ExecutionException, InterruptedException, JsonProcessingException {
        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"multiplication");
        CalculatorRequest result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return getResponseEntity(result.getResult());
    }

    @GetMapping("division")
    public ResponseEntity<Object> division(@RequestParam Float x, @RequestParam Float y) throws ExecutionException, InterruptedException, JsonProcessingException {
        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"division");
        CalculatorRequest result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return getResponseEntity(result.getResult());
    }

    private ResponseEntity<Object> getResponseEntity(String result) {
        return ResponseEntity.ok().body(Map.of(
                "result", result));
    }
}
