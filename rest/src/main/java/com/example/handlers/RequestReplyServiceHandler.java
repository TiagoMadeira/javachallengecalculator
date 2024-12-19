package com.example.handlers;
import com.example.CalculatorMessage;
import com.example.services.CalculatorRequestReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.Map;

import static com.example.utils.stringUtils.StringUtils.isNumeric;

@Component
public class RequestReplyServiceHandler {

    private final CalculatorRequestReplyService calculatorRequestService;

    public RequestReplyServiceHandler(CalculatorRequestReplyService calculatorRequestService) {
        this.calculatorRequestService = calculatorRequestService;
    }

    public  ResponseEntity<Object> handleRequest(CalculatorMessage request) {
        try {
            CalculatorMessage result = calculatorRequestService.calculatorRequestReply(request);
            return isNumeric(result.getResult()) ? buildEntity(result.getResult(),HttpStatus.OK):buildEntity(result.getResult(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return buildEntity("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Object> buildEntity (String result, HttpStatus status){
        return ResponseEntity.status(status).body(Map.of(
                "result", result));
    }

}
