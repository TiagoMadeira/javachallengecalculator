package com.example.services;

import com.example.CalculatorMessage;

import java.util.concurrent.ExecutionException;

public interface CalculatorRequestReplyService {

    CalculatorMessage calculatorRequestReply(CalculatorMessage calculatorMessage) throws ExecutionException, InterruptedException;
}
