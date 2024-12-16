package com.example.unit;

import com.example.domain.CalculatorRequest;
import com.example.fixtures.CalculatorRequestFixtures;
import com.example.handlers.RequestReplyServiceHandler;
import com.example.services.CalculatorRequestReplyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RequestReplyServiceHandler.class)
public class RequestReplyServiceHandlerTest {

    @Mock
    private CalculatorRequestReplyService calculatorRequestService;

    @InjectMocks
    private RequestReplyServiceHandler handler;


    //@Test
    //public void calculatorRequestSumSuccess() throws ExecutionException, InterruptedException {
    //CalculatorRequest resolvedRequest =  CalculatorRequestFixtures.sum();
    //   resolvedRequest.setResult("2");

    //    when(calculatorRequestService.calculatorRequestReply(CalculatorRequestFixtures.sum())).thenReturn(resolvedRequest);

    //    ResponseEntity<Object> expected = ResponseEntity.ok().body(Map.of("result", resolvedRequest.getResult()));

    //    ResponseEntity<Object> result = handler.handleRequest(CalculatorRequestFixtures.sum());

    //    assertEquals(expected, result);
    //}

}
