package com.example.service;

import org.springframework.stereotype.Service;

@Service
public interface CalculatorInterface {

    float sum(float x, float y);
    float subtraction(float x, float y);
    float multiplication(float x, float y);
    float division(float x, float y);
}
