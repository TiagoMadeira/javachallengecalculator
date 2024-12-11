package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements CalculatorInterface{

    @Autowired
    public CalculatorService(){}

    @Override
    public Float sum(float x, float y) {
        return  x + y;
    }

    @Override
    public Float subtraction(float x, float y) {
        return x - y;
    }

    @Override
    public Float multiplication(float x, float y) {
        return x * y;
    }

    @Override
    public float division(float x, float y) throws ArithmeticException {
        return x / y;
    }
}
