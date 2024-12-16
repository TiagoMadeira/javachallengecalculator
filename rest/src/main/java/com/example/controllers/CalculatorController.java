package com.example.controllers;

import com.example.RestApplication;
import com.example.domain.CalculatorRequest;
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

    private static final Logger logger = LogManager.getLogger("lognow");

    private final CalculatorRequestReplyService calculatorRequestService;

    public CalculatorController( CalculatorRequestReplyService calculatorRequestService){
        this.calculatorRequestService = calculatorRequestService;
    }

    @GetMapping("sum")
    public ResponseEntity<Object> sum(
            @RequestParam() BigDecimal x,
            @RequestParam BigDecimal y,
            @RequestParam(required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        logger.info("logged!##############################################");
        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"sum",precision);
        CalculatorRequest result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return getResponseEntity(result.getResult());
    }

    @GetMapping("subtraction")
    public ResponseEntity<Object> subtraction(
            @RequestParam BigDecimal x,
            @RequestParam BigDecimal y,
            @RequestParam(required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"subtraction", precision);
        CalculatorRequest  result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return getResponseEntity(result.getResult());
    }

    @GetMapping("multiplication")
    public ResponseEntity<Object> multiplication(
            @RequestParam BigDecimal x,
            @RequestParam BigDecimal y,
            @RequestParam(required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"multiplication",precision);
        CalculatorRequest result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return getResponseEntity(result.getResult());
    }

    @GetMapping("division")
    public ResponseEntity<Object> division(
            @RequestParam BigDecimal x,
            @RequestParam BigDecimal y,
            @RequestParam(required = false, defaultValue = "0") int precision) throws ExecutionException, InterruptedException {

        CalculatorRequest calculatorRequest = new CalculatorRequest(x,y,"division",precision);
        CalculatorRequest result = calculatorRequestService.calculatorRequestReply(calculatorRequest);

        return getResponseEntity(result.getResult());
    }

    private ResponseEntity<Object> getResponseEntity(String result) {
        return ResponseEntity.ok().body(Map.of(
                "result", result));
    }

    private void logRequest(){


    }
}
