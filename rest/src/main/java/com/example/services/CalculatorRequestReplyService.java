package com.example.services;

import com.example.domain.CalculatorRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.ExecutionException;

public interface CalculatorRequestReplyService {

    CalculatorRequest calculatorRequestReply(CalculatorRequest calculatorRequest) throws ExecutionException, InterruptedException;
}
