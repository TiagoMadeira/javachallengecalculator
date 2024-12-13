package com.example.service.impl;


import com.example.service.CalculatorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements CalculatorInterface {

    @Autowired
    public CalculatorService(){}

    @Override
    public float sum(float x, float y) {
        return  x + y;
    }

    @Override
    public float subtraction(float x, float y) {
        return x - y;
    }

    @Override
    public float multiplication(float x, float y) {
        return x * y;
    }

    @Override
    public float division(float x, float y) throws ArithmeticException {
        return x / y;
    }
}
