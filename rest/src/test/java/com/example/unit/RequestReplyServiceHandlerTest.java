package com.example.unit;

import com.example.CalculatorMessage;
import com.example.fixtures.CalculatorMessageFixtures;
import com.example.handlers.RequestReplyServiceHandler;
import com.example.services.CalculatorRequestReplyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RequestReplyServiceHandler.class)
public class RequestReplyServiceHandlerTest {

    private CalculatorMessage message;
    @Mock
    private CalculatorRequestReplyService calculatorRequestService;

    @InjectMocks
    private RequestReplyServiceHandler handler;

    //For better readability
    @BeforeEach
    void init(){
        message = CalculatorMessageFixtures.sum();
    }

    @Test
    public void calculatorMessageOK() throws ExecutionException, InterruptedException {

        message.setResult("2");
        when(calculatorRequestService.calculatorRequestReply(CalculatorMessageFixtures.sum())).thenReturn(message);

        ResponseEntity<Object> expected = ResponseEntity.ok().body(Map.of("result", message.getResult()));
        ResponseEntity<Object> result = handler.handleRequest(CalculatorMessageFixtures.sum());

        assertEquals(expected.getStatusCode(), result.getStatusCode());
        assertEquals(expected.getBody(), result.getBody());
    }

    @Test
    public void calculatorMessageBADREQUEST() throws ExecutionException, InterruptedException {

        message.setResult("Not Numeric");
        when(calculatorRequestService.calculatorRequestReply(CalculatorMessageFixtures.sum())).thenReturn(message);

        ResponseEntity<Object> expected = ResponseEntity.badRequest().body(Map.of("result", message.getResult()));
        ResponseEntity<Object> result = handler.handleRequest(CalculatorMessageFixtures.sum());

        assertEquals(expected.getStatusCode(), result.getStatusCode());
        assertEquals(expected.getBody(), result.getBody());
    }

    @Test
    public void calculatorMessageERROR() throws ExecutionException, InterruptedException {

        when(calculatorRequestService.calculatorRequestReply(CalculatorMessageFixtures.sum())).thenThrow();

        ResponseEntity<Object> expected = ResponseEntity.internalServerError().body(Map.of("result","Something went wrong!"));
        ResponseEntity<Object> result = handler.handleRequest(CalculatorMessageFixtures.sum());

        assertEquals(expected.getStatusCode(), result.getStatusCode());
        assertEquals(expected.getBody(), result.getBody());
    }

}
