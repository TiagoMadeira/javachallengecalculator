package com.example;

import org.springframework.stereotype.Service;

@Service
public interface CalculatorInterface {

    Float sum(float x, float y);
    Float subtraction(float x, float y);
    Float multiplication(float x, float y);
    float division(float x, float y);
}
